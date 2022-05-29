package com.roquebuarque.smartstocks.stocks.di

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.roquebuarque.smartstocks.di.ActivityKey
import com.roquebuarque.smartstocks.di.ActivityMemberInjector
import com.roquebuarque.smartstocks.di.ViewModelKey
import com.roquebuarque.smartstocks.stocks.data.StockRepositoryImpl
import com.roquebuarque.smartstocks.stocks.domain.provider.StockRepository
import com.roquebuarque.smartstocks.stocks.presentation.StockListViewModel
import com.roquebuarque.smartstocks.stocks.presentation.ui.StockListActivity
import dagger.Binds
import dagger.MembersInjector
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class StockListModule {

    @Binds
    abstract fun provideStockRepository(impl: StockRepositoryImpl): StockRepository

}

@Module
class StockListActivityInjectorModule {

    @Provides
    @IntoMap
    @ActivityKey(StockListActivity::class)
    fun providesStockListActivityMemberInject(
        stockListActivityMemberInjector: MembersInjector<StockListActivity>
    ): ActivityMemberInjector<Activity> {
        return ActivityMemberInjector(stockListActivityMemberInjector as MembersInjector<Activity>)
    }

}

@Module
abstract class StockListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StockListViewModel::class)
    abstract fun bindStockListViewModel(viewModel: StockListViewModel): ViewModel

}