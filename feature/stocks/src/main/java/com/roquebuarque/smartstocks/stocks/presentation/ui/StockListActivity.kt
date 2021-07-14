package com.roquebuarque.smartstocks.stocks.presentation.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.roquebuarque.smartstocks.stocks.R
import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.presentation.StockListState
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

    }

    override fun onStart() {
        super.onStart()
        disposable = viewModel
            .state
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { renderState(it) }
    }

    override fun onStop() {
        disposable.dispose()
        super.onStop()
    }

    private fun renderState(state: StockListState) {
        when (state.syncState) {
            StockListState.SyncState.Content -> {
                adapter.submitList(state.stocks)
            }
            StockListState.SyncState.Empty -> {

            }
            is StockListState.SyncState.Error -> {

            }
            StockListState.SyncState.Loading -> {

            }
        }
    }

    companion object {
        fun start(context: Context): Intent {
            return Intent(context, StockListActivity::class.java)
        }
    }

}