package com.roquebuarque.smartstocks.di.components

import androidx.lifecycle.ViewModelProvider
import com.roquebuarque.smartstocks.di.modules.ViewModelFactoryModule
import dagger.Subcomponent

@Subcomponent(
    modules = [ViewModelFactoryModule::class]
)
interface ViewModelComponent {

    fun getViewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }
}