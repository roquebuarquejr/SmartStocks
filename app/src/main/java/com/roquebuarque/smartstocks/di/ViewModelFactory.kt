package com.roquebuarque.smartstocks.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.roquebuarque.smartstocks.di.components.ViewModelComponent
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val componentFactory: ViewModelComponent.Factory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val component = componentFactory.create()
        val map = component.viewModelMap
        return map[modelClass]?.get() as T
    }
}