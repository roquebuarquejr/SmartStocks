package com.roquebuarque.smartstocks.stocks.domain.provider

import com.roquebuarque.smartstocks.network.Resource
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import io.reactivex.Flowable

/**
 * Public interface for stock repository
 */
interface StockRepository {

    /**
     * get stocks from our remote and local repos
     * @return flowable containing all combined stocks
     */
    fun getStockList(): Flowable<Resource<List<StockDto>>>
}