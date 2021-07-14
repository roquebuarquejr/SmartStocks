package com.roquebuarque.smartstocks.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

@Module
@InstallIn(SingletonComponent::class)
class SchedulersModule {

    @ComputationScheduler
    @Provides
    fun providesComputationScheduler(): Scheduler = Schedulers.computation()
}