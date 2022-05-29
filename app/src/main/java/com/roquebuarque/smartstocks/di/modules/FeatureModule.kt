package com.roquebuarque.smartstocks.di.modules

import com.roquebuarque.smartstocks.stocks.di.StockListModule
import dagger.Module

@Module(
    includes = [
        NavigationModule::class,
        StockListModule::class,
    ]
)
object FeatureModule