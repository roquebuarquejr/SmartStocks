package com.roquebuarque.smartstocks.di.modules

import com.roquebuarque.smartstocks.di.components.ViewModelComponent
import com.roquebuarque.smartstocks.stocks.di.StockListViewModelModule
import dagger.Module

@Module(
    subcomponents = [ViewModelComponent::class],
    includes = [StockListViewModelModule::class]
)
object ViewModuleModule