package com.roquebuarque.smartstocks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.roquebuarque.smartstocks.navigation.StockListLauncher
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var stockListLauncher: StockListLauncher

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.value.create().inject(this)
        super.onCreate(savedInstanceState)
        stockListLauncher.launch(this)
        finish()
    }
}