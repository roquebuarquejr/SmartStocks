package com.roquebuarque.smartstocks

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.roquebuarque.smartstocks.di.ViewModelFactoryProvider
import com.roquebuarque.smartstocks.di.components.ApplicationComponent
import com.roquebuarque.smartstocks.di.components.DaggerApplicationComponent

class StockApplication : Application(),
    ViewModelFactoryProvider {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
            .factory()
            .create(this)

        component.inject(this)

    }

    override fun provideViewModelFactory(activity: Activity): ViewModelProvider.Factory {
        return component
            .getViewModelComponentFactory()
            .create()
            .getViewModelFactory()
    }

}
