package com.roquebuarque.smartstocks.stocks.data

import com.jakewharton.rxrelay2.BehaviorRelay
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import com.roquebuarque.smartstocks.stocks.domain.provider.StockLocal
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockLocalImpl @Inject constructor(): StockLocal {

    private val cache = BehaviorRelay.createDefault(listOf<StockDto>())

    override fun retrieve(): Observable<List<StockDto>> = cache.hide()

    override fun save(stockDto: StockDto) {
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