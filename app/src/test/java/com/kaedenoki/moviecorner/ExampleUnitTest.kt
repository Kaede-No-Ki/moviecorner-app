package com.kaedenoki.moviecorner

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun formatEpisode(){
        val episode = "13Episode"
        val fix = episode.split("E")
        val build = buildString { append(fix[0]).append(" E").append(fix[1]) }
        println(build)
    }

    @Test
    fun testParseEpisode(){
        val text = "Black Clover Episode 145 Subtitle Indonesia"
        val data = text.split("Episode ")
        val episode = data[1].split(" ")
        println(episode[0])
    }

    @Test
    fun testTitleEpisode(){
        val text = "Black Clover Episode 146 Subtitle Indonesia"
        val data = text.split(" Episode ")
        println("${data[0]} , Episode ${data[1].split(" ")[0]}")
    }
}