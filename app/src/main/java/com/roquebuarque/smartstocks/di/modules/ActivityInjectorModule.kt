package com.roquebuarque.smartstocks.di.modules

import com.roquebuarque.smartstocks.di.contributor.ActivityContributorModule
import com.roquebuarque.smartstocks.di.injector.ActivityInjector
import com.roquebuarque.smartstocks.di.factory.ActivityInjectorFactory
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        ActivityContributorModule::class
    ]
)
abstract class ActivityInjectorModule {

    @Binds
    abstract fun bindsActivityInjector(factory: ActivityInjectorFactory): ActivityInjector
}