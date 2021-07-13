package com.roquebuarque.smartstocks.stocks.facade

import com.roquebuarque.smartstocks.network.Message
import com.roquebuarque.smartstocks.network.SocketHandler
import com.roquebuarque.smartstocks.stocks.data.StockService
import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.domain.SupportedStocks
import io.reactivex.Flowable
import io.reactivex.Flowable.just
import io.reactivex.Flowable.zip
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockFacade @Inject constructor(
    private val socketHandler: SocketHandler,
    private val service: StockService
) : SocketHandler by  socketHandler{

    fun fetchAllStocks(): Flowable<List<StockDto>> {
        return whenConnected {
            getAllSupportedStocks()
                .doOnNext { subscribe(Message(it.isin)) }
                .flatMap { just(service.observeStocks()) }
                .let { sources -> zip(sources, { raw -> raw.map { it as StockDto } }) }
        }
    }

    private fun getAllSupportedStocks(): Flowable<SupportedStocks> {
        return Flowable.fromIterable(
            SupportedStocks
                .values()
                .toList()
        )
    }

}