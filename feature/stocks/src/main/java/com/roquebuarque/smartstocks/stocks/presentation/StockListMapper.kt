package com.roquebuarque.smartstocks.stocks.presentation

import com.roquebuarque.smartstocks.network.Resource
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto

/**
 * Handle map resource/result from repository to view state
 */
fun Resource<List<StockDto>>.mapperToState(currentState: StockListState): StockListState {
    return when (status) {
        Resource.Status.SUCCESS -> {
            if (data?.isEmpty() == true) {
                currentState.copy(syncState = StockListState.SyncState.Empty)
            } else {
                currentState.copy(
                    stocks = data?.asUI()?.sortedBy { it.name } ?: emptyList(),
                    syncState = StockListState.SyncState.Content
                )
            }
        }
        Resource.Status.LOADING -> {
            currentState.copy(syncState = StockListState.SyncState.Loading)
        }
        else -> {
            currentState.copy(
                syncState = StockListState
                    .SyncState.Error(
                        throwable?.message ?: "Something went wrong"
                    )
            )
        }
    }
}