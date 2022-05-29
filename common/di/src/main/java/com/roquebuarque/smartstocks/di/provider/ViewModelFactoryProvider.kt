package com.roquebuarque.smartstocks.di.provider

import android.app.Activity
import androidx.lifecycle.ViewModelProvider

interface ViewModelFactoryProvider {

    fun provideViewModelFactory(activity: Activity): ViewModelProvider.Factory
}