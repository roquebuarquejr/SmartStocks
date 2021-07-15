package com.roquebuarque.smartstocks.stocks.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.roquebuarque.smartstocks.stocks.databinding.ItemStockBinding
import com.roquebuarque.smartstocks.stocks.presentation.StockUI

class StockListAdapter : ListAdapter<StockUI, StockListAdapter.StockViewHolder>(StockListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StockViewHolder(ItemStockBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class StockViewHolder(
        private val view: ItemStockBinding
    ) : RecyclerView.ViewHolder(view.root) {

        fun bind(data: StockUI) {
            view.tvStockName.text = data.name
            view.tvStockValue.text = data.price
        }
    }

    private companion object : DiffUtil.ItemCallback<StockUI>() {

        override fun areItemsTheSame(oldItem: StockUI, newItem: StockUI): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: StockUI, newItem: StockUI): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.price == newItem.price
        }
    }


}