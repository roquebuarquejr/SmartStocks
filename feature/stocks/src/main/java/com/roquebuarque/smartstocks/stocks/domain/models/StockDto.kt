package com.roquebuarque.smartstocks.stocks.domain.models

import java.io.Serializable

data class StockDto(val isin: String, var price: Double): Serializable