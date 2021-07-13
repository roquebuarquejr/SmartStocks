package com.roquebuarque.smartstocks.stocks.presentation

import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.domain.SupportedStocks

data class StockUI(val name: String, val price: String)

fun StockDto.asUI(): StockUI {
    return StockUI(
        name = SupportedStocks.getStockFromIsin(isin).name,
        price = price.toString()
    )
}

fun List<StockDto>.asUI(): List<StockUI> {
    return map { it.asUI() }
}
