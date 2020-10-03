package com.kaedenoki.moviecorner.data.anime.model

import com.google.gson.annotations.SerializedName

data class ItemEpisodeAnime(
    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("uploaded_on")
    val uploadedOn: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)