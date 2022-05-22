package com.roquebuarque.smartstocks.di.components

import androidx.lifecycle.ViewModel
import com.roquebuarque.smartstocks.stocks.di.StockListViewModelModule
import dagger.Subcomponent
import javax.inject.Provider

@Subcomponent(modules = [StockListViewModelModule::class])
interface ViewModelComponent {

    val viewModelMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }
}