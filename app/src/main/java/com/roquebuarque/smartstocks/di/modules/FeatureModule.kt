package com.roquebuarque.smartstocks.di.modules

import com.roquebuarque.smartstocks.stocks.di.StockListFeatureModule
import dagger.Module

@Module(
    includes = [
        NavigationModule::class,
        StockListFeatureModule::class,
    ]
)
object FeatureModule