package com.roquebuarque.smartstocks.stocks.presentation


data class StockListState(val stocks: List<StockUI>, val syncState: SyncState){

    sealed class SyncState{

        object Loading: SyncState()

        object Content: SyncState()

        object Empty: SyncState()

        data class Error(val message: String): SyncState()
    }
}


