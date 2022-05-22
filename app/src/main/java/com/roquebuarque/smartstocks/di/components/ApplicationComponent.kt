package com.roquebuarque.smartstocks.di.components

import android.app.Application
import com.roquebuarque.smartstocks.di.modules.NavigationModule
import com.roquebuarque.smartstocks.di.modules.ViewModelModule
import com.roquebuarque.smartstocks.network.NetworkModule
import com.roquebuarque.smartstocks.stocks.di.StockBindModule
import com.roquebuarque.smartstocks.stocks.di.StockComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        NavigationModule::class,
        StockBindModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: Application)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Application): ApplicationComponent

    }

    fun getNavigationSubComponentFactory(): NavigationComponent.Factory

    fun getStockComponentFactory(): StockComponent.Factory
}