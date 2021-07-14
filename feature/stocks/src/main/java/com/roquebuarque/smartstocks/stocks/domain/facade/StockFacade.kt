package com.roquebuarque.smartstocks.stocks.domain.facade

import com.roquebuarque.smartstocks.network.Message
import com.roquebuarque.smartstocks.network.SocketHandler
import com.roquebuarque.smartstocks.stocks.data.StockLocal
import com.roquebuarque.smartstocks.stocks.data.StockService
import com.roquebuarque.smartstocks.stocks.domain.ConnectionFailedException
import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.domain.SupportedStocks
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.WebSocket.Event.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Flowable.*
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockFacade @Inject constructor(
    private val remote: StockService,
    private val local: StockLocal
) : SocketHandler {

    fun fetchAllStocks(): Flowable<List<StockDto>> {
        return whenConnected {
            remote.observeStocks()
                .doOnNext {
                    local.save(it)
                }
                .switchMap {
                    local
                        .retrieve()
                        .toFlowable(BackpressureStrategy.BUFFER)
                }
                .distinctUntilChanged()
        }
    }

    override fun subscribe(message: Message) {
        remote.sendSubscribe(message)
    }

    override fun <T> whenConnected(func: () -> Flowable<T>): Flowable<T> {
        return remote
            .observeWebSocketEvent()
            .flatMap {
                when (it) {
                    is OnConnectionOpened<*> -> {
                        SupportedStocks
                            .values()
                            .toList()
                            .forEach { supportedStocks ->
                                subscribe(Message(supportedStocks.isin))
                            }
                    }
                    is OnMessageReceived -> { empty<Unit>() }
                    is OnConnectionClosing -> throw ConnectionFailedException(it.shutdownReason.reason)
                    is OnConnectionClosed -> throw ConnectionFailedException(it.shutdownReason.reason)
                    is OnConnectionFailed -> throw  it.throwable
                }

                just(Unit)

            }
            .flatMap { func() }
    }

}