package com.kaedenoki.moviecorner.ui.activity.episode

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.material.chip.Chip
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
                if (isFullscreen) hideLayoutFullScreen(btnFullScreen, this)
                else showLayoutFullScreen(btnFullScreen, this)
            }
        }

        val download = listOf("ZippyShare", "Filesim", "Racaty", "Acefile", "Mega", "MegaUp")
        binding.apply {
            download.forEach {
                cgLow.addView(Chip(cgLow.context).apply { text = it })
                cgMedium.addView(Chip(cgMedium.context).apply { text = it })
                cgHigh.addView(Chip(cgHigh.context).apply { text = it })
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

    private fun buildMediaSource(): MediaSource {
        val uri =
            Uri.parse("https://r2---sn-cp1oxu-ngbe.googlevideo.com/videoplayback?expire=1602159445&ei=1ZJ-X4SiDI7QkgbF-JqwAQ&ip=2a04:3543:1000:2310:30da:13ff:fead:6be6&id=67644800ae980a38&itag=18&source=blogger&susc=bl&mime=video/mp4&dur=1450.202&lmt=1601985720850392&txp=1310224&sparams=expire,ei,ip,id,itag,source,susc,mime,dur,lmt&sig=AOq0QJ8wRgIhALHez5PTllRPuRihG1OpJDbCSQZkPIvU1RbZcpH6xvziAiEA70Ke3knXSsdB0XgnYiibIIVoS8rouLV1JLVvHVb96eQ%3D&redirect_counter=1&rm=sn-25gk67l&req_id=65e715eaca4c36e2&cms_redirect=yes&ipbypass=yes&mh=GH&mip=182.253.72.0&mm=31&mn=sn-cp1oxu-ngbe&ms=au&mt=1602130590&mv=m&mvi=2&pl=25&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRgIhAKIggZPanjUcnZjKfxTo-wZRtvXLGlNH22KnDYbrDmDqAiEA1SeICfsCRfM5fCllP0kxQp1HPhSLllRdKsSRRtQ5TnE%3D")
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(this, "exoplayer-code-labs")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }
}