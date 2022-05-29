package com.roquebuarque.smartstocks.di.modules

import com.roquebuarque.smartstocks.launcher.StockListLauncherImpl
import com.roquebuarque.smartstocks.navigation.StockListLauncher
import dagger.Binds
import dagger.Module

@Module
internal abstract class NavigationModule {

    @Binds
    abstract fun bindStockListLauncher(impl: StockListLauncherImpl): StockListLauncher
}