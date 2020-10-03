package com.kaedenoki.moviecorner.helper

import android.view.View

object Helpers {
    fun View.hideView(){
        this.visibility = View.GONE
    }

    fun View.showView(){
        this.visibility = View.VISIBLE
    }

    fun String.formatEpisode(): String {
        val fix = this.split("E")
        return buildString { append(fix[0]).append(" E").append(fix[1]) }
    }

    fun getAlphaForActionBar(scrollY: Int): Int {
        val minDist = 0
        val maxDist = 650
        return when {
            scrollY > maxDist -> {
                255
            }
            scrollY < minDist -> {
                0
            }
            else -> {
                var alpha = 0
                alpha = (255.0 / maxDist * scrollY).toInt()
                alpha
            }
        }
    }

    fun String.getNumEpisode(): String {
        val data = this.split("Episode ")
        val episode = data[1].split(" ")
        return episode[0]
    }
}