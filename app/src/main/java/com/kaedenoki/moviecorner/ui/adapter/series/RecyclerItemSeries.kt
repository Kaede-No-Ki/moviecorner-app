package com.kaedenoki.moviecorner.ui.adapter.series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.series.model.ItemSeries
import com.kaedenoki.moviecorner.databinding.ItemSeriesBinding

class RecyclerItemSeries(val data: List<Any>) : RecyclerView.Adapter<RecyclerItemSeries.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData = data[position] as ItemSeries
        holder.bindView(itemData)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSeriesBinding.bind(itemView)
        fun bindView(itemSeries: ItemSeries) {
            binding.apply {
                ivItemCard.load(itemSeries.thumbnail)
                tvItemTitle.text = itemSeries.title
                tvItemQuality.text = itemSeries.quality
            }
        }
    }
}