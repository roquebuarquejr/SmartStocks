package com.roquebuarque.smartstocks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.roquebuarque.smartstocks.launcher.StockListLauncherImpl
import com.roquebuarque.smartstocks.navigation.StockListLauncher
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    // TODO INJECT
    private val stockListLauncher: StockListLauncher = StockListLauncherImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
       // (application as MainActivityInjectorHelper).inject(this)
        super.onCreate(savedInstanceState)
        stockListLauncher.launch(this)
        finish()
    }
}