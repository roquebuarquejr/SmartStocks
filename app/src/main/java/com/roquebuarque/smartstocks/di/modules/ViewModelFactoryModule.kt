package com.roquebuarque.smartstocks.di.modules

import androidx.lifecycle.ViewModelProvider
import com.roquebuarque.smartstocks.di.DaggerViewModelFactory
import com.roquebuarque.smartstocks.di.components.ViewModelComponent
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}