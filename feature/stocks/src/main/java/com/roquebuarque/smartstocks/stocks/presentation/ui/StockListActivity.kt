package com.roquebuarque.smartstocks.stocks.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.roquebuarque.smartstocks.di.ViewModelFactoryProvider
import com.roquebuarque.smartstocks.stocks.R
import com.roquebuarque.smartstocks.stocks.databinding.ActivityStockListBinding
import com.roquebuarque.smartstocks.stocks.presentation.StockListEvent
import com.roquebuarque.smartstocks.stocks.presentation.StockListState
import com.roquebuarque.smartstocks.stocks.presentation.StockListViewModel
import com.roquebuarque.smartstocks.views.viewBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class StockListActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable
/*
    @Inject
    lateinit var repository: StockRepository*/

    private val viewBinding by viewBinding { ActivityStockListBinding.inflate(it) }
    private val viewModel: StockListViewModel by lazyVM()

    private val adapter by lazy { StockListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // (application as StockInjectorHelper).inject(this)

      /*  println("XABLAU $repository")
        disposable = repository
            .getStockList()
            .subscribe {
                println(it)
            }

*/

        setContentView(R.layout.activity_stock_list)
        bindViews()
    }

    private fun bindViews() {
        with(viewBinding) {
            rvStocks.itemAnimator = null
            rvStocks.adapter = adapter

            ctnMessage.setOnClickListener {
                viewModel.dispatch(StockListEvent.Fetch)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        disposable =
            viewModel
                .state
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { renderState(it) }
    }

    override fun onStop() {
        disposable.dispose()
        super.onStop()
    }

    private fun renderState(state: StockListState) {
        with(viewBinding) {
            when (state.syncState) {
                StockListState.SyncState.Content -> {
                    viewFlipper.displayedChild = CONTENT
                    adapter.submitList(state.stocks)
                }
                StockListState.SyncState.Empty -> {
                    viewFlipper.displayedChild = MESSAGE
                    tvMessage.text = getString(R.string.empty)
                    image.setImageResource(R.drawable.ic_empty)
                }
                is StockListState.SyncState.Error -> {
                    viewFlipper.displayedChild = MESSAGE
                    tvMessage.text = getString(R.string.try_gain, state.syncState.message)
                    image.setImageResource(R.drawable.ic_server_down)
                }
                StockListState.SyncState.Loading -> {
                    viewFlipper.displayedChild = LOADING
                }
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

inline fun <reified VM : ViewModel> ComponentActivity.lazyVM() = viewModels<VM> {
    (application as ViewModelFactoryProvider).provideViewModelFactory(this)
}

