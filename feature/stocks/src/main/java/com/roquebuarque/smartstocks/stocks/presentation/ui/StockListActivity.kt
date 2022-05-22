package com.roquebuarque.smartstocks.stocks.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelStore
import com.roquebuarque.smartstocks.stocks.R
import com.roquebuarque.smartstocks.stocks.databinding.ActivityStockListBinding
import com.roquebuarque.smartstocks.stocks.di.StockComponent
import com.roquebuarque.smartstocks.stocks.di.StockComponentProvider
import com.roquebuarque.smartstocks.stocks.domain.provider.StockRepository
import com.roquebuarque.smartstocks.stocks.presentation.StockListEvent
import com.roquebuarque.smartstocks.stocks.presentation.StockListState
import com.roquebuarque.smartstocks.stocks.presentation.StockListViewModel
import com.roquebuarque.smartstocks.views.viewBinding
import dagger.internal.DaggerGenerated
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class StockListActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable

    lateinit var component: StockComponent
/*
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory*/

    @Inject
    lateinit var repository: StockRepository

    private val viewBinding by viewBinding { ActivityStockListBinding.inflate(it) }
    private val viewModel: StockListViewModel by lazyVM()

    private val adapter by lazy { StockListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = (application as StockComponentProvider)
            .provideComponent()

        component
            .inject(this)

        println("XABLAU $repository")

        disposable = repository
            .getStockList()
            .subscribe {
                println(it)
            }


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
    getStockComponent().getStockListViewModelFactory()
}

private inline fun <reified VM : ViewModel> ComponentActivity.myLovelyViewModels(): Lazy<VM> {
   return lazy(LazyThreadSafetyMode.NONE) {
            getStockComponent()
                .getStockListViewModelFactory()
                .create(VM::class.java)
    }
}

fun ComponentActivity.getStockComponent(): StockComponent =
    (application as StockComponentProvider).provideComponent()
