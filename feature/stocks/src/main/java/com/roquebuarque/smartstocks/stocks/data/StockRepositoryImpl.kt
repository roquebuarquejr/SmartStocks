package com.roquebuarque.smartstocks.stocks.data

import com.roquebuarque.smartstocks.di.ComputationScheduler
import com.roquebuarque.smartstocks.network.Resource
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import com.roquebuarque.smartstocks.stocks.domain.provider.StockFacade
import com.roquebuarque.smartstocks.stocks.domain.provider.StockRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val stockFacade: StockFacade,
    @ComputationScheduler private val computationScheduler: Scheduler
): StockRepository {

    override fun getStockList(): Flowable<Resource<List<StockDto>>> {
        return stockFacade
            .fetchAllStocks()
            .subscribeOn(computationScheduler)
            .map { stocks -> Resource.success(stocks) }
            .skip(1)
            .startWith(Resource.loading())
            .onErrorReturn { throwable -> Resource.error(throwable) }
    }

}