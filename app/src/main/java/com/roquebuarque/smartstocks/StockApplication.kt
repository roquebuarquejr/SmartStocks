package com.roquebuarque.smartstocks

import android.app.Activity
import android.app.Application
import com.roquebuarque.smartstocks.di.components.ApplicationComponent
import com.roquebuarque.smartstocks.di.components.DaggerApplicationComponent
import com.roquebuarque.smartstocks.stocks.di.StockComponent
import com.roquebuarque.smartstocks.stocks.di.StockComponentProvider

class StockApplication : Application(), StockComponentProvider {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()


        component = DaggerApplicationComponent
            .factory()
            .create(this)


        component.inject(this)
    }

    override fun provideComponent(): StockComponent {
        return component.getStockComponentFactory().create()
    }
}

val Activity.injector
    get() = lazy {
        (application as StockApplication).component.getNavigationSubComponentFactory()
    }

