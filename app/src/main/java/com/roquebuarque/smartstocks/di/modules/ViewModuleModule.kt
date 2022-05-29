package com.roquebuarque.smartstocks.di.modules

import com.roquebuarque.smartstocks.di.components.ViewModelComponent
import com.roquebuarque.smartstocks.stocks.di.StockListFeatureModule
import dagger.Module

@Module(
    subcomponents = [ViewModelComponent::class],
    includes = [StockListFeatureModule::class]
)
object ViewModuleModule