package com.roquebuarque.smartstocks.stocks.data

import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.tinder.scarlet.ws.Receive
import io.reactivex.Flowable

interface StockService {

    @Receive
    fun observeStocks(): Flowable<StockDto>
}