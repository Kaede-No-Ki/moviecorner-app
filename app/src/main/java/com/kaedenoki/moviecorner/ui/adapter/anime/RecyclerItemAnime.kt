package com.kaedenoki.moviecorner.ui.adapter.anime

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.anime.model.ItemAnime
import com.kaedenoki.moviecorner.databinding.ItemAnimeBinding
import com.kaedenoki.moviecorner.helper.Helpers.formatEpisode
import com.kaedenoki.moviecorner.helper.Helpers.hideView
import com.kaedenoki.moviecorner.ui.activity.detail.DetailActivity

class RecyclerItemAnime(
    val context: Context,
    val data: List<Any>
) :
    RecyclerView.Adapter<RecyclerItemAnime.ViewHolder>() {
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
        holder.bindView(context, itemData)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAnimeBinding.bind(itemView)
        fun bindView(context: Context, itemAnime: ItemAnime) {
            binding.apply {
                ivItemCard.load(itemAnime.thumb) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(10f))
                }
                tvItemTitle.text = itemAnime.title

                itemAnime.dayUpdated.let {
                    tvRelease.text = buildString {
                        if (it != null) append(itemAnime.dayUpdated).append(" • ")
                        append(itemAnime.uploadedOn)
                    }
                }
                itemAnime.score.let {
                    val textEpisode = itemAnime.episode
                    if (it != null) {
                        tvEpisode.text = textEpisode!!.formatEpisode()
                        tvScore.text = it.toString()
                    } else {
                        tvEpisode.text = textEpisode
                        layoutScore.hideView()
                    }
                }
                cardSeries.setOnClickListener {
                    context.startActivity(Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_ID, itemAnime.id)
                    })
                }
            }
        }
    }
}