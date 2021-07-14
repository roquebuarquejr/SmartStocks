package com.roquebuarque.smartstocks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.roquebuarque.smartstocks.navigation.StockListLauncher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var stockListLauncher: StockListLauncher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stockListLauncher.launch(this)
        finish()
    }
}