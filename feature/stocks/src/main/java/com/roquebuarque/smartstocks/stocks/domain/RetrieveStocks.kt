package com.roquebuarque.smartstocks.stocks.domain

import com.roquebuarque.smartstocks.stocks.data.StockRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrieveStocks @Inject constructor(private val repository: StockRepository) {

    private val cached by lazy { fetchAll() }

    fun execute(): Flowable<List<StockDto>> = cached

    private fun fetchAll(): Flowable<List<StockDto>> {
        return repository
            .getStockList()
    }


}