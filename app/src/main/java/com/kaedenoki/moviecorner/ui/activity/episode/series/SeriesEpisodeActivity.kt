package com.kaedenoki.moviecorner.ui.activity.episode.series

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.http.SslError
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.series.response.DetailEpisodeResponse
import com.kaedenoki.moviecorner.databinding.ActivitySeriesEpisodeBinding
import com.kaedenoki.moviecorner.ui.activity.episode.anime.AnimeEpisodeActivity
import com.kaedenoki.moviecorner.ui.adapter.series.episode.UrlSeriesAdapter
import kotlinx.android.synthetic.main.activity_series_episode.*

class SeriesEpisodeActivity : AppCompatActivity() {
    val viewModel: SeriesEpisodeViewModel by viewModels()
    var shouldLoad = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_series_episode)

        val episodeId = intent.getStringExtra(AnimeEpisodeActivity.EXTRA_ID) ?: ""

        initPlayer(this)
        viewModel.getEpisode(episodeId)

        viewModel.url().observe(this, Observer {
            player.loadUrl(it)
            loader.visibility = View.VISIBLE
            shouldLoad = true
        })

        viewModel.episode().observe(this, Observer {
            setRecyclerStreaming(it.urlStreaming)
            setRecyclerDownload(it.urlDownload)
        })


    }

    private fun setRecyclerDownload(urlDownload: List<DetailEpisodeResponse.Data.UrlStreaming>) {
        rvDownload.layoutManager = GridLayoutManager(this, 3)
        rvDownload.adapter = UrlSeriesAdapter(urlDownload) {
            Toast.makeText(this, "Anda menekan download", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerStreaming(urlStreaming: List<DetailEpisodeResponse.Data.UrlStreaming>) {
        rvUrlStreaming.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvUrlStreaming.adapter = UrlSeriesAdapter(urlStreaming) {
            viewModel.setUrlStreaming(it)
        }
    }


    private fun initPlayer(activity: Activity) {
        player.webChromeClient = MyChrome(activity)
        player.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (shouldLoad) {
                    view.loadUrl(url)
                }
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                loader.visibility = View.GONE
                shouldLoad = false
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.proceed()
            }
        }
        player.settings.javaScriptEnabled = true
        player.settings.allowFileAccess = true
        player.settings.allowContentAccess = true
        player.settings.domStorageEnabled = true
        player.settings.javaScriptCanOpenWindowsAutomatically = false
        player.settings.pluginState = WebSettings.PluginState.ON
        player.settings.loadsImagesAutomatically = true
        player.settings.blockNetworkLoads = false
        player.settings.blockNetworkImage = false
        player.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            player.settings.mediaPlaybackRequiresUserGesture = false
        }
        player.settings.userAgentString =
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36"

    }

    private class MyChrome internal constructor(val activity: Activity) : WebChromeClient() {
        private var mCustomView: View? = null
        private var mCustomViewCallback: CustomViewCallback? = null
        protected var mFullscreenContainer: FrameLayout? = null
        private var mOriginalOrientation = 0
        private var mOriginalSystemUiVisibility = 0
        override fun getDefaultVideoPoster(): Bitmap? {
            return if (mCustomView == null) {
                null
            } else BitmapFactory.decodeResource(activity.applicationContext.resources, 2130837573)
        }

        override fun onHideCustomView() {
            (activity.window.decorView as FrameLayout).removeView(mCustomView)
            mCustomView = null
            activity.window.decorView.systemUiVisibility = mOriginalSystemUiVisibility
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            mCustomViewCallback!!.onCustomViewHidden()
            mCustomViewCallback = null
        }


        override fun onShowCustomView(
            paramView: View?,
            paramCustomViewCallback: CustomViewCallback?
        ) {
            if (mCustomView != null) {
                onHideCustomView()
                return
            }
            Log.d("TAG", "onShowCustomView: diklik")
            mCustomView = paramView
            mOriginalSystemUiVisibility = activity.window.decorView.systemUiVisibility

            mOriginalOrientation = activity.requestedOrientation
            mCustomViewCallback = paramCustomViewCallback
            (activity.window.decorView as FrameLayout).addView(
                mCustomView,
                FrameLayout.LayoutParams(-1, -1)
            )
            activity.window.decorView.systemUiVisibility = 3846 or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        player.saveState(outState)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        player.restoreState(savedInstanceState)
    }
}