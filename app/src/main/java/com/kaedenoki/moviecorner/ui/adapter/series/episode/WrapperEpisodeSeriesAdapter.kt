package com.kaedenoki.moviecorner.ui.adapter.series.episode

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.series.model.ItemEpisodeSeries
import com.kaedenoki.moviecorner.databinding.ItemWrapperEpisodeSeriesBinding

class WrapperEpisodeSeriesAdapter(
    val context: Context,
    val episodeSeries: List<ItemEpisodeSeries>
) :
    RecyclerView.Adapter<WrapperEpisodeSeriesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(context: Context, itemEpisodeSeries: ItemEpisodeSeries) {
            val binding = ItemWrapperEpisodeSeriesBinding.bind(itemView)
            binding.apply {
                titleSeason.text = "Season ${itemEpisodeSeries.season}"
                rvEpisode.layoutManager = GridLayoutManager(context, 4)
                rvEpisode.adapter = SeriesEpisodeAdapter(context, itemEpisodeSeries.data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_wrapper_episode_series, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(context, episodeSeries[position])
    }

    override fun getItemCount(): Int = episodeSeries.size
}