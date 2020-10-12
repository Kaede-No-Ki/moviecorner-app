package com.kaedenoki.moviecorner.ui.adapter.series

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.series.model.ItemSeries
import com.kaedenoki.moviecorner.databinding.ItemSeriesBinding
import com.kaedenoki.moviecorner.ui.activity.detail.DetailActivity

class RecyclerItemSeries(val data: List<Any>, val context: Context) : RecyclerView.Adapter<RecyclerItemSeries.ViewHolder>() {
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
        holder.bindView(itemData, context)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSeriesBinding.bind(itemView)
        fun bindView(itemSeries: ItemSeries, context: Context) {
            binding.apply {
                ivItemCard.load(itemSeries.thumbnail)
                tvItemTitle.text = itemSeries.title
                tvItemQuality.text = itemSeries.quality


                cardSeries.setOnClickListener {
                    context.startActivity(Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_ID, itemSeries.id)
                    })
                }
            }
        }
    }
}