package com.kaedenoki.moviecorner.ui.activity.detail

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.anime.response.ResponseDetailAnime
import com.kaedenoki.moviecorner.databinding.ActivityDetailBinding
import com.kaedenoki.moviecorner.databinding.ContentScrollingBinding
import com.kaedenoki.moviecorner.databinding.LayoutEpisodeAnimeBinding
import com.kaedenoki.moviecorner.helper.Helpers.getAlphaForActionBar
import com.kaedenoki.moviecorner.ui.adapter.anime.episode.AnimeEpisodeAdapter


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var contentBinding: ContentScrollingBinding
    private lateinit var episodeAdapter: AnimeEpisodeAdapter
    private val model: DetailAnimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        contentBinding = binding.contentScrolling
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val cd = ColorDrawable(ContextCompat.getColor(this, R.color.bgBlack))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setBackgroundDrawable(cd)
        }
        cd.alpha = 0

        val id = intent.getStringExtra(EXTRA_ID)
        model.initData(id!!)

        model.getAnime().observe(this, Observer { anime ->
            applyDataAnime(anime)
        })

        contentBinding.apply {
            nsvDetail
                .setOnScrollChangeListener { v: NestedScrollView?, _: Int, _: Int, _: Int, _: Int ->
                    cd.alpha = getAlphaForActionBar(v!!.scrollY)
                }
        }

        binding.contentShowEpisode.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.layout_episode_anime, null)
            val dialogBinding = LayoutEpisodeAnimeBinding.bind(view)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            val behaviour = BottomSheetBehavior.from(view.parent as View)
                .apply {
                    peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
                    expandedOffset = binding.toolbar.height
                }
            dialogBinding.rvEpisode.apply {
                adapter = episodeAdapter
            }
            dialog.show()
        }
    }

    private fun applyDataAnime(anime: ResponseDetailAnime?) {
        contentBinding.apply {
            anime.let {
                supportActionBar?.title = it!!.title
                ivBackground.load(it.thumb) { crossfade(true) }
                ivPoster.load(it.thumb) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(8f))
                }
                tvTitle.text = it.title
                tvTitleJP.text = it.japanase
                tvRating.text = it.score
                tvDuration.text = it.duration
                it.genreList!!.forEach {
                    chipGroup.addView(Chip(chipGroup.context).apply { text = it!!.genreName })
                }
                tvType.text = it.type
                tvStatus.text = it.status
                tvProducer.text = it.producer
                tvStudio.text = it.studio
                tvRelease.text = it.releaseDate
                tvTotalEps.text =
                    buildString { append("Total Episode: ").append(it.totalEpisode ?: "-") }

                episodeAdapter = AnimeEpisodeAdapter(this@DetailActivity, it.episodeList)
            }
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}