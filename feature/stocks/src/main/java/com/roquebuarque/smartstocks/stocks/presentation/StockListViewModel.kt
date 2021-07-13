package com.roquebuarque.smartstocks.stocks.presentation

import androidx.lifecycle.ViewModel
import com.roquebuarque.smartstocks.stocks.data.StockService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(val service: StockService) : ViewModel() {
}