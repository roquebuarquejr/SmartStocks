package com.roquebuarque.smartstocks.di.modules

import com.roquebuarque.smartstocks.di.ActivityInjector
import com.roquebuarque.smartstocks.di.ActivityInjectorFactory
import com.roquebuarque.smartstocks.di.components.ActivityComponent
import com.roquebuarque.smartstocks.stocks.di.StockListActivityInjectorModule
import com.roquebuarque.smartstocks.stocks.di.StockListModule
import dagger.Binds
import dagger.Module

@Module(
    subcomponents = [ActivityComponent::class],
    includes = [
        NavigationModule::class,
        StockListModule::class,
        StockListActivityInjectorModule::class
    ]
)
object ActivityModule


@Module
abstract class ActivityInjectorModule {

    @Binds
    abstract fun bindsActivityInjector(factory: ActivityInjectorFactory): ActivityInjector
}