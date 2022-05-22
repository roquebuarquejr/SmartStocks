package com.roquebuarque.smartstocks.launcher

import android.app.Activity
import com.roquebuarque.smartstocks.navigation.StockListLauncher
import com.roquebuarque.smartstocks.stocks.StockListProvider
import javax.inject.Inject

class StockListLauncherImpl @Inject constructor() : StockListLauncher {

    override fun launch(activity: Activity) {
        activity.startActivity(StockListProvider.getStockListIntent(activity))
    }
}