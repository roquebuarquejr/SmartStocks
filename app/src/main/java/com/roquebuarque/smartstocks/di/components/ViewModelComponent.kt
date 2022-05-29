package com.roquebuarque.smartstocks.di.components

import androidx.lifecycle.ViewModelProvider
import com.roquebuarque.smartstocks.di.scopes.ActivityScope
import dagger.Subcomponent

@Subcomponent
interface ViewModelComponent {

    fun getViewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }
}