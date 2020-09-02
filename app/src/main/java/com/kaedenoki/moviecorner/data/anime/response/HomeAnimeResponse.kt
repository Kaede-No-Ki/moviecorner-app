package com.kaedenoki.moviecorner.data.anime.response


import com.google.gson.annotations.SerializedName
import com.kaedenoki.moviecorner.data.anime.model.ItemAnime

data class HomeAnimeResponse(
    @SerializedName("baseUrl")
    val baseUrl: String? = null,
    @SerializedName("home")
    val home: Home? = null,
    @SerializedName("status")
    val status: String? = null
)

data class Home(
    @SerializedName("complete")
    val complete: List<ItemAnime>? = null,
    @SerializedName("on_going")
    val onGoing: List<ItemAnime>? = null
)