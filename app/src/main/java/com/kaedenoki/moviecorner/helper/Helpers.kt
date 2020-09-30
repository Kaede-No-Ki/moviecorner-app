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
}