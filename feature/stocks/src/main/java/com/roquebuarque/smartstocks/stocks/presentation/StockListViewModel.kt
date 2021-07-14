package com.roquebuarque.smartstocks.stocks.presentation

import com.jakewharton.rxrelay2.BehaviorRelay
import com.roquebuarque.smartstocks.StateViewModel
import com.roquebuarque.smartstocks.stocks.data.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val repository: StockRepository
) : StateViewModel<StockListEvent, StockListState>() {

    private val action = BehaviorRelay.createDefault<StockListEvent>(StockListEvent.Fetch)
    override val state: Observable<StockListState> =
        action
            .switchMap { event ->
                when(event){
                    StockListEvent.Fetch -> fetchStocks().toObservable()
                }
            }

    private fun fetchStocks(): Flowable<StockListState> {
       return repository
            .getStockList()
            .map { stockList ->
                StockListState(
                    stocks =
                    stockList
                        .asUI()
                        .sortedBy { stock ->
                            stock.name
                        },
                    syncState = StockListState.SyncState.Content
                )
            }

    }
    override fun dispatch(event: StockListEvent) {
        action.accept(event)
    }
}