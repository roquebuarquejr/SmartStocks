package com.roquebuarque.smartstocks.stocks.presentation

import com.roquebuarque.smartstocks.network.Resource
import com.roquebuarque.smartstocks.stocks.domain.models.StockDto

fun Resource<List<StockDto>>.mapperToState(currentState: StockListState): StockListState {
    return if (status == Resource.Status.SUCCESS) {
        when {
            data != null && data?.isNotEmpty() == true -> {
                currentState.copy(
                    stocks = data?.asUI()?.sortedBy { it.name } ?: emptyList(),
                    syncState = StockListState.SyncState.Content
                )
            }
            data != null && data?.isEmpty() == true -> {
                currentState.copy(syncState = StockListState.SyncState.Empty)
            }
            else -> {
                currentState
            }
        }

    } else {
        currentState.copy(
            syncState = StockListState
                .SyncState.Error(
                    throwable?.message ?: "Something went wrong"
                )
        )
    }
}