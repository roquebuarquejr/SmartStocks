package com.roquebuarque.smartstocks.stocks.domain.provider

import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import io.reactivex.Flowable

/**
 * Public interface to fetch stock list
 */
interface StockFacade {

    /**
     * Provides a flowable with list of stocks from remote
     * @return flowable containing all stocks combined
     */
    fun fetchAllStocks(): Flowable<List<StockDto>>
}