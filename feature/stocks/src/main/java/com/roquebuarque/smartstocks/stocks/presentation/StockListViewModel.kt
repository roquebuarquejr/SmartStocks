package com.roquebuarque.smartstocks.stocks.presentation

import androidx.lifecycle.ViewModel
import com.roquebuarque.smartstocks.stocks.data.StockRepository
import com.roquebuarque.smartstocks.stocks.data.StockService
import com.roquebuarque.smartstocks.stocks.domain.RetrieveStocks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(val retrieveStocks: RetrieveStocks) : ViewModel() {

    val state =  retrieveStocks
        .execute()
        .map { stockList ->
            stockList
                .asUI()
                .sortedBy { stock ->
                    stock.name
                }
        }
}