package com.roquebuarque.smartstocks.stocks.domain

import java.io.Serializable

data class StockDto(val isin: String, val price: Double): Serializable
