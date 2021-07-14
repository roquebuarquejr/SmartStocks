package com.roquebuarque.smartstocks.stocks.facade

import android.util.Log
import com.roquebuarque.smartstocks.network.Message
import com.roquebuarque.smartstocks.network.SocketHandler
import com.roquebuarque.smartstocks.stocks.data.StockService
import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.domain.SupportedStocks
import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable
import io.reactivex.Flowable.*
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockFacade @Inject constructor(
    private val service: StockService
) : SocketHandler {

    fun fetchAllStocks(): Flowable<List<StockDto>> {
        return whenConnected {
            getAllSupportedStocks()
                .doOnNext {
                    subscribe(Message(it.isin))
                }
                .flatMap {
                    just(
                        service.observeStocks()
                            .take(3)
                            .doOnNext {
                                Log.d("Roque", it.toString())
                            }
                    )
                }.let { sources ->
                    zip(sources, Zipper)
                        .doOnNext {
                            Log.d("Roque", it.toString())
                        }
                }

        }
    }

    private companion object Zipper : Function<Array<Any>, List<StockDto>> {
        override fun apply(raw: Array<Any>) = raw.map { it as StockDto }
    }

    private fun getAllSupportedStocks(): Flowable<SupportedStocks> {
        return fromIterable(
            SupportedStocks
                .values()
                .toList()
        )
    }

    override fun subscribe(message: Message) {
        service.sendSubscribe(message)
    }

    override fun <T> whenConnected(func: () -> Flowable<T>): Flowable<T> {
        return service
            .observeWebSocketEvent()
            .filter { it is WebSocket.Event.OnConnectionOpened<*> }
            .flatMap { func() }
    }

}