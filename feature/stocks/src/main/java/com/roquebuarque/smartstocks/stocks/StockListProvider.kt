package com.roquebuarque.smartstocks.stocks

import android.content.Context
import android.content.Intent
import com.roquebuarque.smartstocks.stocks.presentation.ui.StockListActivity

object StockListProvider {

    fun getStockListIntent(context: Context): Intent {
        return StockListActivity.start(context)
    }
}