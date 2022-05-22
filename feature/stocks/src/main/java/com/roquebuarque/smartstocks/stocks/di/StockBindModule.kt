package com.roquebuarque.smartstocks.stocks.di

import com.roquebuarque.smartstocks.stocks.data.StockRepositoryImpl
import com.roquebuarque.smartstocks.stocks.domain.provider.StockRepository
import dagger.Binds
import dagger.Module

@Module(subcomponents = [StockComponent::class])
abstract class StockBindModule {

    @Binds
    abstract fun provideStockRepository(impl: StockRepositoryImpl): StockRepository
}