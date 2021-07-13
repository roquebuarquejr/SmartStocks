package com.roquebuarque.smartstocks.stocks.domain

import java.lang.IllegalArgumentException

enum class SupportedStocks(val isin: String) {

  APPLE("US0378331005"),
  ADIDAS("DE000A1EWWW0"),
  DAIMLER("DE0007100000");

  companion object{

    fun getStockFromIsin(isin: String): SupportedStocks {
      return when(isin){
        APPLE.isin -> APPLE
        ADIDAS.isin -> ADIDAS
        DAIMLER.isin -> DAIMLER
        else -> throw IllegalArgumentException("Unknown isin $isin")
      }
    }
  }
}