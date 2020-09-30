package com.kaedenoki.moviecorner.repository.network.anime

import com.kaedenoki.moviecorner.data.anime.response.BaseAnimeResponse
import com.kaedenoki.moviecorner.data.anime.response.HomeAnimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeContract {
    @GET("home")
    fun getHome(): Call<HomeAnimeResponse>

    @GET("complete/page/{num}")
    fun getCompleteAnime(
        @Path("num")
        page: Int?
    ): Call<BaseAnimeResponse>
}