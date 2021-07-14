package com.roquebuarque.smartstocks.stocks.presentation.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.roquebuarque.smartstocks.stocks.R
import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.presentation.StockListEvent
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
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var tvMessage: TextView
    private lateinit var loading: ProgressBar

    private val adapter by lazy { StockListAdapterUI() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_list)
        bindViews()
    }

    private fun bindViews() {
        rvStocks = findViewById(R.id.rvStocks)
        viewFlipper = findViewById(R.id.viewFlipper)
        tvMessage = findViewById(R.id.tvMessage)
        loading = findViewById(R.id.progress)

        rvStocks.itemAnimator = null
        rvStocks.adapter = adapter

        tvMessage.setOnClickListener {
            viewModel.dispatch(StockListEvent.Fetch)
        }
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
                viewFlipper.displayedChild = CONTENT
                adapter.submitList(state.stocks)
            }
            StockListState.SyncState.Empty -> {
                viewFlipper.displayedChild = MESSAGE
                tvMessage.text = getString(R.string.empty)
            }
            is StockListState.SyncState.Error -> {
                viewFlipper.displayedChild = MESSAGE
                tvMessage.text = getString(R.string.try_gain, state.syncState.message)
            }
            StockListState.SyncState.Loading -> {
                viewFlipper.displayedChild = LOADING
            }
        }
    }

    companion object {

        private const val CONTENT = 0
        private const val MESSAGE = 1
        private const val LOADING = 2

        fun start(context: Context): Intent {
            return Intent(context, StockListActivity::class.java)
        }
    }

}