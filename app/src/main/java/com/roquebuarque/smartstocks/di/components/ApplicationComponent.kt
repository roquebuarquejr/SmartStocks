package com.roquebuarque.smartstocks.di.components

import android.app.Application
import com.roquebuarque.smartstocks.di.modules.*
import com.roquebuarque.smartstocks.network.NetworkModule
import com.roquebuarque.smartstocks.stocks.di.StockListActivityInjectorModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ActivityModule::class,
        ViewModuleModule::class,
        ViewModelFactoryModule::class,
        ActivityInjectorModule::class,
        StockListActivityInjectorModule::class
    ]
)
interface ApplicationComponent {

    fun getViewModelComponentFactory(): ViewModelComponent.Factory

    fun getActivityComponentFactory(): ActivityComponent.Factory

    fun inject(application: Application)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Application): ApplicationComponent
    }

}