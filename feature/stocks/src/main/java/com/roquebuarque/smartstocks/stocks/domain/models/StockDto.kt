package com.roquebuarque.smartstocks.stocks.domain.models

import java.io.Serializable

/**
 * Hold values coming from remote source
 *
 * @param isin unique identifier per stock
 * @param price current value of the stock
 */
data class StockDto(val isin: String, var price: Double): Serializable