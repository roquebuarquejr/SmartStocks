package com.roquebuarque.smartstocks.stocks.presentation

import com.jakewharton.rxrelay2.BehaviorRelay
import com.roquebuarque.smartstocks.StateViewModel
import com.roquebuarque.smartstocks.stocks.domain.provider.StockRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

class StockListViewModel @Inject constructor(
    private val repository: StockRepository
) : StateViewModel<StockListEvent, StockListState>() {

    private val action = BehaviorRelay.createDefault<StockListEvent>(StockListEvent.Fetch)
    override val state: Flowable<StockListState> =
        action
            .toFlowable(BackpressureStrategy.BUFFER)
            .onBackpressureBuffer(10000)
            .switchMap { event ->
                when (event) {
                    StockListEvent.Fetch -> fetchStocks()
                }
            }

    private fun fetchStocks(): Flowable<StockListState> {
        return repository
            .getStockList()
            .scan(StockListState(),
                { state, result -> result.mapperToState(state) }
            )
    }

    override fun dispatch(event: StockListEvent) {
        action.accept(event)
    }
}