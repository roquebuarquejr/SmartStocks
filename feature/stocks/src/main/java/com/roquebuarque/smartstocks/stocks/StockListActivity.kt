package com.roquebuarque.smartstocks.stocks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.roquebuarque.smartstocks.network.Message
import com.roquebuarque.smartstocks.stocks.data.StockService
import com.roquebuarque.smartstocks.stocks.presentation.StockListViewModel
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StockListActivity : AppCompatActivity() {

    private val viewModel: StockListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_list)

        viewModel.service.observeWebSocketEvent()
            .filter { it is WebSocket.Event.OnConnectionOpened<*> }
            .subscribe {
                viewModel.service.sendSubscribe(Message("US0378331005"))
            }

        viewModel.service.observeStocks()
            .subscribe {
               Log.d(this::class.java.name, it.toString())
            }
    }
}