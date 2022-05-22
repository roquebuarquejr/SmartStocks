package com.roquebuarque.smartstocks.stocks.di

import androidx.lifecycle.ViewModel
import com.roquebuarque.smartstocks.di.ViewModelKey
import com.roquebuarque.smartstocks.stocks.presentation.StockListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StockListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StockListViewModel::class)
    abstract fun bindStockListViewModel(viewModel: StockListViewModel): ViewModel
}