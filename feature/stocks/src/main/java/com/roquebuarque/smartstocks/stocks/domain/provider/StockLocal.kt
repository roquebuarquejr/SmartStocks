package com.roquebuarque.smartstocks.stocks.domain.provider

import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import io.reactivex.Observable

interface StockLocal {

    fun retrieve(): Observable<List<StockDto>>

    fun save(stockDto: StockDto)
}