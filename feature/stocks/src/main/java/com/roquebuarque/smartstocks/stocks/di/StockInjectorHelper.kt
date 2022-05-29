package com.roquebuarque.smartstocks.stocks.di

import com.roquebuarque.smartstocks.stocks.presentation.ui.StockListActivity
import dagger.MembersInjector

interface StockActivityInjectorProvider {

    fun inject(stockListActivity: StockListActivity)
}
