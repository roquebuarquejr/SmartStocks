package com.roquebuarque.smartstocks.stocks.data

import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.facade.StockFacade
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(private val stockFacade: StockFacade) {

    fun getStockList(): Observable<List<StockDto>> {
        return stockFacade.fetchAllStocks().toObservable()
    }

}