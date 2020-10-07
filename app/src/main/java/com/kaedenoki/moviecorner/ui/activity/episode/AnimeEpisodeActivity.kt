package com.kaedenoki.moviecorner.ui.activity.episode

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.kaedenoki.moviecorner.databinding.ActivityAnimeEpisodeBinding


class AnimeEpisodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeEpisodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeEpisodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mediaSourceFactory = buildMediaSource()

        val player = SimpleExoPlayer.Builder(this).build()
        player.apply {
            prepare(mediaSourceFactory)
            playWhenReady = false
        }
        binding.videoView.player = player

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