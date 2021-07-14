package com.roquebuarque.smartstocks.stocks.di

import com.roquebuarque.smartstocks.stocks.data.StockLocalImpl
import com.roquebuarque.smartstocks.stocks.data.StockRepositoryImpl
import com.roquebuarque.smartstocks.stocks.domain.facade.StockFacadeImpl
import com.roquebuarque.smartstocks.stocks.domain.provider.StockFacade
import com.roquebuarque.smartstocks.stocks.domain.provider.StockLocal
import com.roquebuarque.smartstocks.stocks.domain.provider.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StockBindModule {

    @Binds
    @Singleton
    abstract fun provideStockRepository(impl: StockRepositoryImpl): StockRepository

    @Binds
    @Singleton
    abstract fun provideStockLocal(impl: StockLocalImpl): StockLocal

    @Binds
    @Singleton
    abstract fun provideStockFacade(impl: StockFacadeImpl): StockFacade

}