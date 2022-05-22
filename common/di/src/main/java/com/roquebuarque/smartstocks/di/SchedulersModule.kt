package com.roquebuarque.smartstocks.di

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulersModule {

    fun providesComputationScheduler(): Scheduler = Schedulers.computation()
}