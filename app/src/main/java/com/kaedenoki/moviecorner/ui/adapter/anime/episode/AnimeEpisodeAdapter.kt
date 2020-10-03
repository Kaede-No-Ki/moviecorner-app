package com.kaedenoki.moviecorner.ui.adapter.anime.episode

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.anime.model.ItemEpisodeAnime
import com.kaedenoki.moviecorner.databinding.ItemEpisodeAnimeBinding
import com.kaedenoki.moviecorner.helper.Helpers.getNumEpisode

class AnimeEpisodeAdapter(
    val context: Context,
    val episode: List<ItemEpisodeAnime?>?
) : RecyclerView.Adapter<AnimeEpisodeAdapter.AnimeEpisodeViewHolder>() {
    class AnimeEpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view)
    private lateinit var binding : ItemEpisodeAnimeBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeEpisodeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_episode_anime, parent, false)
        binding = ItemEpisodeAnimeBinding.bind(view)
        return AnimeEpisodeViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AnimeEpisodeViewHolder, position: Int) {
        binding.tvEpisode.text = episode?.get(position)?.title!!.getNumEpisode()
    }

    override fun getItemCount(): Int = episode!!.size

}