package com.roquebuarque.smartstocks.stocks.presentation

sealed class StockListEvent{

    object Fetch: StockListEvent()
}
