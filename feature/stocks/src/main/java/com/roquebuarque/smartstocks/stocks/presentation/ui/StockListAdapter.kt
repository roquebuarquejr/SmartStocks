package com.roquebuarque.smartstocks.stocks.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.roquebuarque.smartstocks.stocks.R
import com.roquebuarque.smartstocks.stocks.domain.StockDto
import com.roquebuarque.smartstocks.stocks.domain.SupportedStocks

class StockListAdapter : ListAdapter<StockDto, StockListAdapter.StockViewHolder>(StockListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_stock, parent, false)
        return StockViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class StockViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        private lateinit var tvName: TextView
        private lateinit var tvValue: TextView

        fun bind(data: StockDto) {

            tvName = view.findViewById(R.id.tvStockName)
            tvValue = view.findViewById(R.id.tvStockValue)

            tvName.text = SupportedStocks.getStockFromIsin(data.isin).name
            tvValue.text = data.price.toString()
        }
    }


    private companion object : DiffUtil.ItemCallback<StockDto>() {

        override fun areItemsTheSame(oldItem: StockDto, newItem: StockDto): Boolean {
            return oldItem.isin == newItem.isin &&
                    oldItem.price.equals(newItem.price)
        }

        override fun areContentsTheSame(oldItem: StockDto, newItem: StockDto): Boolean {
            return oldItem.isin == newItem.isin
        }
    }


}