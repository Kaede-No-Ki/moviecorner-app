package com.kaedenoki.moviecorner.ui.adapter.anime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.anime.model.ItemAnime
import com.kaedenoki.moviecorner.data.series.model.ItemSeries
import com.kaedenoki.moviecorner.databinding.ItemAnimeBinding
import com.kaedenoki.moviecorner.databinding.ItemSeriesBinding

class RecyclerItemAnime(val data: List<Any>) : RecyclerView.Adapter<RecyclerItemAnime.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData = data[position] as ItemAnime
        holder.bindView(itemData)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAnimeBinding.bind(itemView)
        fun bindView(itemAnime: ItemAnime) {
            binding.apply {
                ivItemCard.load(itemAnime.thumb)
                tvItemTitle.text = itemAnime.title
                tvItemSubtitle1.text = itemAnime.episode
                tvItemSubtitle2.text = itemAnime.dayUpdated
            }
        }
    }
}