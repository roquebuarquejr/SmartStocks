package com.roquebuarque.smartstocks.stocks.domain.facade

import com.roquebuarque.smartstocks.network.Message
import com.roquebuarque.smartstocks.network.SocketHandler
import com.roquebuarque.smartstocks.stocks.domain.provider.StockService
import com.roquebuarque.smartstocks.stocks.domain.models.ConnectionFailedException
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import com.roquebuarque.smartstocks.stocks.domain.provider.StockLocal
import com.roquebuarque.smartstocks.stocks.domain.models.SupportedStocks
import com.roquebuarque.smartstocks.stocks.domain.provider.StockFacade
import com.tinder.scarlet.WebSocket.Event.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Flowable.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockFacadeImpl @Inject constructor(
    private val remote: StockService,
    private val local: StockLocal
) : SocketHandler, StockFacade {

    override fun fetchAllStocks(): Flowable<List<StockDto>> {
        return whenConnected {
            remote.observeStocks()
                .distinctUntilChanged()
                .delay(500, TimeUnit.MILLISECONDS)
                .doOnNext {
                    local.save(it)
                }
                .switchMap {
                    local
                        .retrieve()
                        .toFlowable(BackpressureStrategy.LATEST)
                }
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