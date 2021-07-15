package com.roquebuarque.smartstocks.stocks.domain.provider

import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import io.reactivex.Observable

/**
 * Public interface for our simple memory cache
 */
interface StockLocal {

    /**
     * Retrieve stocks from memory cache
     * @return observable containing all combined stocks
     */
    fun retrieve(): Observable<List<StockDto>>

    /**
     * Save new stock coming from remote to our local cache
     * @param stockDto to be stored or updated
     */
    fun save(stockDto: StockDto)
}