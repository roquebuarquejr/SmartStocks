package com.roquebuarque.smartstocks.stocks.presentation

import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.domain.SupportedStocks
import java.text.DecimalFormat

data class StockUI(val name: String, val price: String)

fun StockDto.asUI(): StockUI {
    return StockUI(
        name = SupportedStocks.getStockFromIsin(isin).name,
        price = price.toPrice()
    )
}

fun List<StockDto>.asUI(): List<StockUI> {
    return map { it.asUI() }
}

fun Double.toPrice(): String {
    val pattern = "#,###.00"
    val decimalFormat = DecimalFormat(pattern)
    decimalFormat.groupingSize = 3

    return decimalFormat.format(this)
}