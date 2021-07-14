package com.roquebuarque.smartstocks.stocks.data

import com.roquebuarque.smartstocks.di.IoScheduler
import com.roquebuarque.smartstocks.network.Resource
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import com.roquebuarque.smartstocks.stocks.domain.provider.StockRepository
import com.roquebuarque.smartstocks.stocks.domain.provider.StockFacade
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val stockFacade: StockFacade,
    @IoScheduler private val ioScheduler: Scheduler
): StockRepository {

    override fun getStockList(): Flowable<Resource<List<StockDto>>> {
        return stockFacade
            .fetchAllStocks()
            .subscribeOn(ioScheduler)
            .map { stocks -> Resource.success(stocks) }
            .onErrorReturn { throwable -> Resource.error(throwable) }
    }

}