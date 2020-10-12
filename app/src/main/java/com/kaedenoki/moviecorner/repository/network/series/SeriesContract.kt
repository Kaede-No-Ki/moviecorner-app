package com.kaedenoki.moviecorner.repository.network.series

import com.kaedenoki.moviecorner.data.series.response.DetailEpisodeResponse
import com.kaedenoki.moviecorner.data.series.response.DetailSeries
import com.kaedenoki.moviecorner.data.series.response.HomeSeriesResponse
import com.kaedenoki.moviecorner.data.series.response.Ping
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SeriesContract {
    @GET("/")
    fun getHome(): Call<HomeSeriesResponse>

    @GET("/ping")
    fun ping(): Call<Ping>

    @GET("series/{id}")
    fun getDetailSeries(
        @Path("id")
        id: String?
    ): Call<DetailSeries>

    @GET("series/episode/{id}")
    fun getEpisode(
        @Path("id")
        id: String?
    ) : Call<DetailEpisodeResponse>
}