package com.roquebuarque.smartstocks.stocks.di

import androidx.lifecycle.ViewModel
import com.roquebuarque.smartstocks.di.key.ViewModelKey
import com.roquebuarque.smartstocks.stocks.data.StockRepositoryImpl
import com.roquebuarque.smartstocks.stocks.domain.provider.StockRepository
import com.roquebuarque.smartstocks.stocks.presentation.StockListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        StockListModule::class,
        StockListViewModelModule::class
    ]
)
object StockListFeatureModule

@Module
internal abstract class StockListModule {

    @Binds
    abstract fun provideStockRepository(impl: StockRepositoryImpl): StockRepository

}


@Module
internal abstract class StockListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StockListViewModel::class)
    abstract fun bindStockListViewModel(viewModel: StockListViewModel): ViewModel

}