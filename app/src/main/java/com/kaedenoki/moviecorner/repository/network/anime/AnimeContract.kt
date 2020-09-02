package com.kaedenoki.moviecorner.repository.network.anime

import com.kaedenoki.moviecorner.data.anime.response.HomeAnimeResponse
import com.kaedenoki.moviecorner.data.series.response.HomeSeriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface AnimeContract {
    @GET("/api/home")
    fun getHome(): Call<HomeAnimeResponse>
}