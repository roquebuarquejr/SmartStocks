package com.roquebuarque.smartstocks.di.modules

import com.roquebuarque.smartstocks.di.ActivityInjector
import com.roquebuarque.smartstocks.di.ActivityInjectorFactory
import com.roquebuarque.smartstocks.stocks.di.StockListActivityInjectorModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [StockListActivityInjectorModule::class]
)
abstract class ActivityInjectorModule {

    @Binds
    abstract fun bindsActivityInjector(factory: ActivityInjectorFactory): ActivityInjector
}