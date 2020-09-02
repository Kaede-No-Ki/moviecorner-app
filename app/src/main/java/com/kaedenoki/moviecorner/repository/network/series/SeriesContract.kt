package com.kaedenoki.moviecorner.repository.network.series

import com.kaedenoki.moviecorner.data.series.response.HomeSeriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface SeriesContract {
    @GET("/")
    fun getHome(): Call<HomeSeriesResponse>
}