package com.roquebuarque.smartstocks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.roquebuarque.smartstocks.di.extensions.injectMe
import com.roquebuarque.smartstocks.di.injector.ActivityInjector
import com.roquebuarque.smartstocks.navigation.StockListLauncher
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var stockListLauncher: StockListLauncher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectMe()
        stockListLauncher.launch(this)
        finish()
    }
}