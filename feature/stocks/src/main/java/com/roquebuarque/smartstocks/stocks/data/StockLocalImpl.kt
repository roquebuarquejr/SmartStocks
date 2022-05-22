package com.roquebuarque.smartstocks.stocks.data

import com.jakewharton.rxrelay2.BehaviorRelay
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import com.roquebuarque.smartstocks.stocks.domain.provider.StockLocal
import io.reactivex.Observable
import javax.inject.Inject

class StockLocalImpl @Inject constructor() : StockLocal {

    private val cache = BehaviorRelay.createDefault(mutableListOf<StockDto>())
    override fun retrieve(): Observable<List<StockDto>> = cache.map { it.toList() }.hide()

    override fun save(stockDto: StockDto) {
        createOrUpdate(stockDto)
    }

    private fun createOrUpdate(stockDto: StockDto) {
        cache.value?.find { it.isin == stockDto.isin }?.let {
            it.price = stockDto.price
        } ?: run {
            cache.value?.add(stockDto)
        }
        cache.accept(cache.value ?: mutableListOf())
    }

}