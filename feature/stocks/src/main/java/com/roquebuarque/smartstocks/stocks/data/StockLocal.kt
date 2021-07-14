package com.roquebuarque.smartstocks.stocks.data

import com.jakewharton.rxrelay2.BehaviorRelay
import com.roquebuarque.smartstocks.stocks.domain.StockDto
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockLocal @Inject constructor() {

    private val cache = BehaviorRelay.createDefault(listOf<StockDto>())

    fun retrieve(): Observable<List<StockDto>> = cache.hide()

    fun save(stockDto: StockDto) {
        createOrUpdate(stockDto)
    }

    private fun createOrUpdate(stockDto: StockDto) {
        val tempList = cache.value?.toMutableList() ?: mutableListOf()
        tempList.find { it.isin == stockDto.isin }?.let {
            tempList.remove(it)
        }
        tempList.add(stockDto)
        cache.accept(tempList.toList())
    }

}