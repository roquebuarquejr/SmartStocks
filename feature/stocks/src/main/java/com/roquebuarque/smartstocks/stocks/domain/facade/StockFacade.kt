package com.roquebuarque.smartstocks.stocks.domain.facade

import com.roquebuarque.smartstocks.network.Message
import com.roquebuarque.smartstocks.network.SocketHandler
import com.roquebuarque.smartstocks.stocks.data.StockLocal
import com.roquebuarque.smartstocks.stocks.data.StockService
import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.domain.SupportedStocks
import com.tinder.scarlet.WebSocket
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

    private companion object Zipper : Function<Array<Any>, List<StockDto>> {
        override fun apply(raw: Array<Any>) = raw.map { it as StockDto }
    }

    override fun subscribe(message: Message) {
        remote.sendSubscribe(message)
    }

    override fun <T> whenConnected(func: () -> Flowable<T>): Flowable<T> {
        return remote
            .observeWebSocketEvent()
            .filter { it is WebSocket.Event.OnConnectionOpened<*> }
            .doOnNext {
                SupportedStocks
                    .values()
                    .toList()
                    .forEach {
                        subscribe(Message(it.isin))
                    }
            }
            .flatMap { func() }
    }

}