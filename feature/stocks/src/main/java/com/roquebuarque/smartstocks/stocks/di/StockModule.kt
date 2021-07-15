package com.roquebuarque.smartstocks.stocks.di

import com.google.gson.Gson
import com.roquebuarque.smartstocks.stocks.domain.provider.StockService
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

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

}