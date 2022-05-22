package com.roquebuarque.smartstocks.di.modules

import androidx.lifecycle.ViewModelProvider
import com.roquebuarque.smartstocks.di.ViewModelFactory
import com.roquebuarque.smartstocks.di.components.ViewModelComponent
import dagger.Binds
import dagger.Module

@Module(
    subcomponents = [ViewModelComponent::class]
)
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}