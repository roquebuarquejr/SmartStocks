package com.roquebuarque.smartstocks.stocks.domain

import java.io.Serializable

data class StockDto(val isin: String, val price: Double): Serializable{

    override fun equals(other: Any?)
            = (other is StockDto)
            && isin == other.isin

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
