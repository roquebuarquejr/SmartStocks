package com.roquebuarque.smartstocks.stocks.presentation

import com.roquebuarque.smartstocks.network.Resource
import com.roquebuarque.smartstocks.stocks.domain.StockDto

fun Resource<List<StockDto>>.mapperToState(currentState: StockListState): StockListState {
    return if (status == Resource.Status.SUCCESS) {
        data
            ?.takeUnless { it.isEmpty() }
            ?.let { list ->
                currentState.copy(
                    stocks = list.asUI().sortedBy { it.name },
                    syncState = StockListState.SyncState.Content
                )
            } ?: currentState.copy(syncState = StockListState.SyncState.Empty)
    } else {
        currentState.copy(
            syncState = StockListState
                .SyncState.Error(
                    throwable?.message ?: "Something went wrong"
                )
        )
    }
}