package com.roquebuarque.smartstocks.stocks.di

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.roquebuarque.smartstocks.di.ActivityKey
import com.roquebuarque.smartstocks.di.Injector
import com.roquebuarque.smartstocks.di.ViewModelKey
import com.roquebuarque.smartstocks.stocks.data.StockRepositoryImpl
import com.roquebuarque.smartstocks.stocks.domain.provider.StockRepository
import com.roquebuarque.smartstocks.stocks.presentation.StockListViewModel
import com.roquebuarque.smartstocks.stocks.presentation.ui.StockListActivity
import dagger.Binds
import dagger.MembersInjector
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Inject

@Module
abstract class StockListModule {

    @Binds
    abstract fun provideStockRepository(impl: StockRepositoryImpl): StockRepository

}

@Module
abstract class StockListActivityInjectorModule{

   /* @Binds
    @IntoMap
    @ActivityKey(StockListActivity::class)
    abstract fun bindStockListActivityMemberInjector(impl: StockListMemberInjector): Injector
*/
}

@Module
abstract class StockListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StockListViewModel::class)
    abstract fun bindStockListViewModel(viewModel: StockListViewModel): ViewModel

}