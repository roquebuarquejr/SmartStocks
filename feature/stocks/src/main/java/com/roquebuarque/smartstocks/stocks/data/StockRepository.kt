package com.roquebuarque.smartstocks.stocks.data

import com.roquebuarque.smartstocks.di.IoScheduler
import com.roquebuarque.smartstocks.network.Resource
import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.domain.facade.StockFacade
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(
    private val stockFacade: StockFacade,
    @IoScheduler private val ioScheduler: Scheduler
) {

    fun getStockList(): Flowable<Resource<List<StockDto>>> {
        return stockFacade
            .fetchAllStocks()
            .subscribeOn(ioScheduler)
            .map { stocks -> Resource.success(stocks) }
            .onErrorReturn { throwable -> Resource.error(throwable) }
    }

}