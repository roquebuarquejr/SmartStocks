package com.roquebuarque.smartstocks.launcher

import com.roquebuarque.smartstocks.navigation.StockListLauncher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class LauncherModule {

    @Binds
    abstract fun bindStockListLauncher(impl: StockListLauncherImpl): StockListLauncher
}