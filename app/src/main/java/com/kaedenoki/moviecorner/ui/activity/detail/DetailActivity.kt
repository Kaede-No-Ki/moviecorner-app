package com.kaedenoki.moviecorner.ui.activity.detail

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.anime.response.ResponseDetailAnime
import com.kaedenoki.moviecorner.data.series.response.DetailSeries
import com.kaedenoki.moviecorner.databinding.ActivityDetailBinding
import com.kaedenoki.moviecorner.databinding.ContentScrollingBinding
import com.kaedenoki.moviecorner.databinding.LayoutEpisodeAnimeBinding
import com.kaedenoki.moviecorner.helper.Const.MODE_ANIME
import com.kaedenoki.moviecorner.helper.Helpers.getAlphaForActionBar
import com.kaedenoki.moviecorner.repository.local.HawkStore
import com.kaedenoki.moviecorner.ui.adapter.anime.episode.AnimeEpisodeAdapter
import com.kaedenoki.moviecorner.ui.adapter.series.episode.WrapperEpisodeSeriesAdapter


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var contentBinding: ContentScrollingBinding
    private lateinit var episodeAnimeAdapter: AnimeEpisodeAdapter
    private lateinit var episodeSeriesAdapter: WrapperEpisodeSeriesAdapter
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

        model.initData(id!!, HawkStore.getMode(this))
        if (HawkStore.getMode(this) == MODE_ANIME) {
            model.getAnime().observe(this, Observer { anime ->
                applyDataAnime(anime)
            })
        } else {
            model.getSeries().observe(this, Observer {
                applyDataSeries(it)
            })
        }

        model.getErrorLoad().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            finish()
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
                if (HawkStore.getMode(this@DetailActivity) == MODE_ANIME) {
                    adapter = episodeAnimeAdapter
                    layoutManager = GridLayoutManager(this@DetailActivity,4)

                } else {
                    adapter = episodeSeriesAdapter
                    layoutManager = LinearLayoutManager(this@DetailActivity)
                }

            }
            dialog.show()
        }
    }

    private fun applyDataSeries(series: DetailSeries?) {
        contentBinding.apply {
            series.let {
                supportActionBar?.title = it?.data?.title
                ivBackground.load(it?.data?.banner) { crossfade(true) }
                ivPoster.load(it?.data?.thumbnail) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(8f))
                }
                tvTitle.text = it?.data?.title
                tvTitleJP.text = it?.data?.descriptions?.country

                tvRating.text = "${it?.data?.descriptions?.tmdb}/10"
                tvDuration.text = it?.data?.descriptions?.duration
                it?.data?.descriptions?.genre?.split(",")?.forEach {
                    chipGroup.addView(Chip(chipGroup.context).apply { text = it })
                }

                it?.data?.descriptions?.views?.let { release ->
                    tvType.text = "$release views"
                }
                tvStatus.text = it?.data?.descriptions?.tvstatus
                tvProducer.text = "Director : ${it?.data?.descriptions?.director}"
                tvStudio.text = "Studio : ${it?.data?.descriptions?.studio}"
                tvRelease.text = "Actors : ${it?.data?.descriptions?.actors}"
                tvTotalEps.text = "Release : ${it?.data?.descriptions?.release}"

                episodeSeriesAdapter =
                    WrapperEpisodeSeriesAdapter(this@DetailActivity, it?.data?.episodes!!)
            }
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

                tvSinopsis.text = it.synopsis

                episodeAnimeAdapter = AnimeEpisodeAdapter(this@DetailActivity, it.episodeList)
            }
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}