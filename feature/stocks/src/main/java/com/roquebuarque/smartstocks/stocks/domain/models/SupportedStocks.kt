package com.roquebuarque.smartstocks.stocks.domain.models

import java.lang.IllegalArgumentException

enum class SupportedStocks(val isin: String) {

  APPLE("US0378331005"),
  ADIDAS("DE000A1EWWW0"),
  DAIMLER("DE0007100000"),
  NETFLIX("US64110L1061"),
  NIKE("US6541061031"),
  AMAZON("US0231351067"),
  SIEMENS("DE0007236101"),
  BAYER("DE000BAY0017"),
  VOLKSWAGEN("DE0007664005"),
  TESLA("US88160R1014"),
  HEINEKEN("NL0000009165"),
  BMW("XS1168962063"),
  UBER("US90353T1007"),
  UBERR("AAAA"),
  SAMSUNG("US7960542030");

  companion object{

    fun getStockFromIsin(isin: String): SupportedStocks {
      return when(isin){
        APPLE.isin -> APPLE
        ADIDAS.isin -> ADIDAS
        DAIMLER.isin -> DAIMLER
        NETFLIX.isin -> NETFLIX
        NIKE.isin -> NIKE
        AMAZON.isin -> AMAZON
        HEINEKEN.isin -> HEINEKEN
        BMW.isin -> BMW
        UBER.isin -> UBER
        SAMSUNG.isin -> SAMSUNG
        SIEMENS.isin -> SIEMENS
        BAYER.isin -> BAYER
        VOLKSWAGEN.isin -> VOLKSWAGEN
        TESLA.isin -> TESLA
        UBERR.isin -> UBERR
        else -> throw IllegalArgumentException("Unknown isin $isin")

      }
    }
  }
}