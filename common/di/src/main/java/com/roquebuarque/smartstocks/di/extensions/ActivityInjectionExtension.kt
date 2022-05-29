package com.roquebuarque.smartstocks.di.extensions

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.roquebuarque.smartstocks.di.injector.ActivityInjector
import com.roquebuarque.smartstocks.di.provider.ViewModelFactoryProvider

inline fun <reified VM : ViewModel> ComponentActivity.lazyVM() = viewModels<VM> {
    (application as ViewModelFactoryProvider).provideViewModelFactory(this)
}

fun ComponentActivity.injectMe(){
    (application as ActivityInjector).inject(this)
}