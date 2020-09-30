package com.kaedenoki.moviecorner.data.anime.model


import com.google.gson.annotations.SerializedName

data class ItemAnime(
    @SerializedName("day_updated")
    val dayUpdated: String? = null,
    @SerializedName("episode")
    val episode: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("link")
    val link: String? = null,
    @SerializedName("thumb")
    val thumb: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("uploaded_on")
    val uploadedOn: String? = null,
    @SerializedName("score")
    val score : Float? = null
)