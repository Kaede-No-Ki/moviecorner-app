package com.kaedenoki.moviecorner.ui.activity.episode.anime

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.load
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.material.chip.Chip
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.anime.response.Quality
import com.kaedenoki.moviecorner.databinding.ActivityAnimeEpisodeBinding
import com.kaedenoki.moviecorner.helper.Helpers.getTextEpisode
import com.kaedenoki.moviecorner.helper.Helpers.getTitleEpisode
import kotlinx.android.synthetic.main.layout_playback_control_view.view.*

class AnimeEpisodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeEpisodeBinding
    private lateinit var exoPlayer: SimpleExoPlayer
    private val model: AnimeEpisodeViewModel by viewModels()
    private var isFullscreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeEpisodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val episodeId = intent.getStringExtra(EXTRA_ID) ?: ""
        model.apply {
            getAnimeEpisode(episodeId)
            getEpisode().observe(this@AnimeEpisodeActivity, Observer {
                setupVideo(it.linkStream)
                setupView(it.title)
                setupLinkDownload(it.quality)
            })
        }
    }

    private fun setupLinkDownload(download: Quality?) {

        binding.apply {
            download?.lowQuality?.downloadLinks?.forEach {
                cgLow.addView(Chip(cgLow.context).apply { text = it?.host })
            }
            download?.mediumQuality?.downloadLinks?.forEach {
                cgMedium.addView(Chip(cgMedium.context).apply { text = it?.host })
            }
            download?.highQuality?.downloadLinks?.forEach {
                cgHigh.addView(Chip(cgHigh.context).apply { text = it?.host })
            }
        }
    }

    private fun setupView(title: String?) {
        binding.apply {
            tvTitle.text = title?.getTitleEpisode()
            tvEpisode.text = title?.getTextEpisode()
        }
    }

    private fun setupVideo(linkStream: String?) {
        val mediaSourceFactory = buildMediaSource(linkStream ?: "")
        exoPlayer = SimpleExoPlayer.Builder(this).build()
        exoPlayer.apply {
            prepare(mediaSourceFactory)
            playWhenReady = false
        }

        binding.videoView.apply {
            player = exoPlayer
            val btnFullScreen = this.exo_fullscreen_icon
            this.exo_fullscreen_button.setOnClickListener {
                if (isFullscreen) hideLayoutFullScreen(btnFullScreen, this)
                else showLayoutFullScreen(btnFullScreen, this)
            }
        }
    }

    private fun showLayoutFullScreen(btnFullScreen: ImageView, playerView: PlayerView) {
        btnFullScreen.load(R.drawable.exo_controls_fullscreen_exit)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

        if (supportActionBar != null) supportActionBar?.hide()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        val params = playerView.layoutParams
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT

        playerView.layoutParams = params
        isFullscreen = true
    }

    private fun hideLayoutFullScreen(btnFullScreen: ImageView, playerView: PlayerView) {
        btnFullScreen.load(R.drawable.exo_controls_fullscreen_enter)
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

        if (supportActionBar != null) supportActionBar?.show()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val params = playerView.layoutParams
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT

        playerView.layoutParams = params
        isFullscreen = false
    }

    private fun buildMediaSource(url: String): MediaSource {
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(this, "exoplayer-code-labs")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(url))
    }

    override fun onPause() {
        super.onPause()
        exoPlayer.playWhenReady = false
    }

    override fun onDestroy() {
        exoPlayer.release()
        super.onDestroy()
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}