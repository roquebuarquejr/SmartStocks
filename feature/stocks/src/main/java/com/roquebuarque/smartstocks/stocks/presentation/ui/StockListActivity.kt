package com.roquebuarque.smartstocks.stocks.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.roquebuarque.smartstocks.stocks.R
import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.presentation.StockListViewModel
import com.roquebuarque.smartstocks.stocks.presentation.StockUI
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

@AndroidEntryPoint
class StockListActivity : AppCompatActivity() {

    private val viewModel: StockListViewModel by viewModels()
    private lateinit var disposable: Disposable

    private lateinit var rvStocks: RecyclerView
    private val adapter by lazy { StockListAdapterUI() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_list)

        rvStocks = findViewById(R.id.rvStocks)
        rvStocks.itemAnimator = null
        rvStocks.adapter = adapter

        disposable = viewModel
            .state
            .subscribe { renderState(it) }

    }

    private fun renderState(list: List<StockUI>) {
        Log.d("Roque", list.toString())
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}