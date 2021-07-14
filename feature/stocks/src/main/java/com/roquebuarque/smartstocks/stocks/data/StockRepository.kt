package com.roquebuarque.smartstocks.stocks.data

import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.domain.facade.StockFacade
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(private val stockFacade: StockFacade) {

    fun getStockList(): Flowable<List<StockDto>> {
        return stockFacade.fetchAllStocks()
    }

}