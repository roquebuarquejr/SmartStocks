package com.roquebuarque.smartstocks.stocks.presentation

/**
 * Wrap all possible view states for stock list
 */
data class StockListState(val stocks: List<StockUI> = emptyList(), val syncState: SyncState = SyncState.Loading){

    /**
     * Wrap synchronization between remote server action and view states
     */
    sealed class SyncState{

        /**
         * Display progress bar
         */
        object Loading: SyncState()

        /**
         * Display stock list recyclerview
         */
        object Content: SyncState()

        /**
         * Display empty message to the user
         */
        object Empty: SyncState()

        /**
         * Display all possibles error message
         */
        data class Error(val message: String): SyncState()
    }
}


