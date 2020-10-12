package com.kaedenoki.moviecorner.ui.adapter.series.episode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.series.model.ItemEpisodeSeries
import com.kaedenoki.moviecorner.databinding.ItemEpisodeAnimeBinding
import com.kaedenoki.moviecorner.ui.activity.episode.anime.AnimeEpisodeActivity
import com.kaedenoki.moviecorner.ui.activity.episode.series.SeriesEpisodeActivity

class SeriesEpisodeAdapter(
    val context: Context,
    private val episodes: List<ItemEpisodeSeries.Data>
) : RecyclerView.Adapter<SeriesEpisodeAdapter.SeriesEpisodeViewHolder>() {
    class SeriesEpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(episode: ItemEpisodeSeries.Data) {
            val binding = ItemEpisodeAnimeBinding.bind(itemView)
            binding.tvEpisode.apply {
                text = episode.episode
                setOnClickListener {
                    context.startActivity(Intent(context, SeriesEpisodeActivity::class.java)
                        .apply { putExtra(AnimeEpisodeActivity.EXTRA_ID, episode.id) })
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesEpisodeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_episode_anime, parent, false)

        return SeriesEpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesEpisodeViewHolder, position: Int) {
        holder.bindView(episodes[position])
    }

    override fun getItemCount(): Int = episodes.size



}