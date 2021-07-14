package com.roquebuarque.smartstocks.stocks.domain.provider

import com.roquebuarque.smartstocks.network.Resource
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import io.reactivex.Flowable

interface StockRepository {

    fun getStockList(): Flowable<Resource<List<StockDto>>>
}