package com.kaedenoki.moviecorner.ui.activity.episode

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.databinding.ActivityAnimeEpisodeBinding
import kotlinx.android.synthetic.main.layout_playback_control_view.view.*


class AnimeEpisodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeEpisodeBinding
    private var isFullscreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeEpisodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mediaSourceFactory = buildMediaSource()

        val exoPlayer = SimpleExoPlayer.Builder(this).build()
        exoPlayer.apply {
            prepare(mediaSourceFactory)
            playWhenReady = false
        }
        binding.videoView.apply {
            player = exoPlayer
            val btnFullScreen = this.exo_fullscreen_icon
            this.exo_fullscreen_button.setOnClickListener {
                if (isFullscreen) {
                    btnFullScreen.load(R.drawable.exo_controls_fullscreen_enter)

                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    if (supportActionBar != null) {
                        supportActionBar?.show()
                    }

                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    val params = this.layoutParams
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    this.layoutParams = params
                    isFullscreen = false
                } else {
                    btnFullScreen.load(R.drawable.exo_controls_fullscreen_exit)
                    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                    if (supportActionBar != null) {
                        supportActionBar?.hide()
                    }

                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    val params = this.layoutParams
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    this.layoutParams = params
                    isFullscreen = true
                }
            }
        }

    }

    private fun buildMediaSource(): MediaSource {
        val uri =
            Uri.parse("https://r2---sn-cp1oxu-ngbe.googlevideo.com/videoplayback?expire=1602117589&ei=Ve99X7DQD9i9kwbGsJ3QCw&ip=2a04:3543:1000:2310:30da:13ff:fead:6be6&id=67644800ae980a38&itag=18&source=blogger&susc=bl&mime=video/mp4&dur=1450.202&lmt=1601985720850392&txp=1310224&sparams=expire,ei,ip,id,itag,source,susc,mime,dur,lmt&sig=AOq0QJ8wRQIhAJvVxPv73IiBAX3YN148PlpLFebL-O48GM62BsSr4D27AiAW9lK7FM4v6T-bfg4d3UMLI6vk8vkt34j3_PTbFdfx8Q%3D%3D&redirect_counter=1&rm=sn-25gk67l&req_id=cbccda367c6536e2&cms_redirect=yes&ipbypass=yes&mh=GH&mip=182.253.72.0&mm=31&mn=sn-cp1oxu-ngbe&ms=au&mt=1602088612&mv=m&mvi=2&pl=25&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRAIgMCDx6UzrsVvXqkKV9iLB-Ll8YyNNpKb6F5TH3_Xcir4CIAQ0QfjX6xdE2bazrcMkW6QOdpNMO4GbUq3wnyi3V4Yo")
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(this, "exoplayer-code-labs")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }
}