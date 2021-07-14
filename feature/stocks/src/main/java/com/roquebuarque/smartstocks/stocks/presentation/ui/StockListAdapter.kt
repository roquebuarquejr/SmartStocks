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
import com.roquebuarque.smartstocks.stocks.presentation.StockUI
import java.text.DecimalFormat

class StockListAdapterUI : ListAdapter<StockUI, StockListAdapterUI.StockViewHolder>(StockListAdapterUI) {

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

        fun bind(data: StockUI) {

            tvName = view.findViewById(R.id.tvStockName)
            tvValue = view.findViewById(R.id.tvStockValue)

            tvName.text = data.name
            tvValue.text = data.price
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