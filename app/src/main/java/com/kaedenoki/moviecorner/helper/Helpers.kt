package com.kaedenoki.moviecorner.helper

import android.view.View
import com.kaedenoki.moviecorner.helper.Helpers.getTitleEpisode

object Helpers {
    fun View.hideView() {
        this.visibility = View.GONE
    }

    fun View.showView() {
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

    val imgMode = listOf(
        "https://images2.alphacoders.com/742/thumb-1920-742320.png",
        "https://assets.st-note.com/production/uploads/images/25980292/picture_pc_1d466b6781c87b2fd537736dc38775f4.jpg"
    )

    fun String.getTitleEpisode(): String {
        val data = this.split(" Episode ")
        return data[0]
    }

    fun String.getTextEpisode(): String {
        val data = this.split(" Episode ")
        return "Episode ${data[1].split(" ")[0]}"
    }
}