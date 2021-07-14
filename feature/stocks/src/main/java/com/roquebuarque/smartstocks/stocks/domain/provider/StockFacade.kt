package com.roquebuarque.smartstocks.stocks.domain.provider

import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import io.reactivex.Flowable

interface StockFacade {

    fun fetchAllStocks(): Flowable<List<StockDto>>
}