package com.roquebuarque.smartstocks.stocks.di

import androidx.lifecycle.ViewModelProvider
import com.roquebuarque.smartstocks.di.ActivityScope
import com.roquebuarque.smartstocks.stocks.presentation.ui.StockListActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
@ActivityScope
interface StockComponent {

    @Subcomponent.Factory
    interface Factory{

        fun create(): StockComponent
    }

    fun inject(stockListActivity: StockListActivity)

    fun getStockListViewModelFactory(): ViewModelProvider.Factory

}