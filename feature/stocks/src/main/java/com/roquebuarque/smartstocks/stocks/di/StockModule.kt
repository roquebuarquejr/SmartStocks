package com.roquebuarque.smartstocks.stocks.di

import com.roquebuarque.smartstocks.stocks.data.StockService
import com.tinder.scarlet.Scarlet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StockModule {

       @Provides
       @Singleton
       fun provideStockService(scarlet: Scarlet): StockService {
           return scarlet.create(service = StockService::class.java)
       }

}