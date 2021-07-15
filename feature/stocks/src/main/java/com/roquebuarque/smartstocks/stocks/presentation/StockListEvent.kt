package com.roquebuarque.smartstocks.stocks.presentation

/**
 * Wrap possible events from stock list view
 */
sealed class StockListEvent{

    /**
     * Trigger when launch the screen to fetch
     * the stock list
     */
    object Fetch: StockListEvent()
}
