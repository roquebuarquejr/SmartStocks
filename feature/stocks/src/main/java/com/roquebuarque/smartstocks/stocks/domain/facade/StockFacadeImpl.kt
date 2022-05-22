package com.roquebuarque.smartstocks.stocks.domain.facade

import com.roquebuarque.smartstocks.stocks.domain.models.StockDto
import com.roquebuarque.smartstocks.stocks.domain.provider.StockFacade
import com.roquebuarque.smartstocks.stocks.domain.provider.StockLocal
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Handle websockets connection
 * and provide entry point to get
 * all stocks list
 *
 * @param remote responsible to fetch stocks from websockets and also
 * provide connection stream
 *
 * @param local simple cache to combine all retrieved stocks
 */
class StockFacadeImpl @Inject constructor(
    private val local: StockLocal
) : StockFacade {

    override fun fetchAllStocks(): Flowable<List<StockDto>> {
        return Flowable.empty()
    }

}