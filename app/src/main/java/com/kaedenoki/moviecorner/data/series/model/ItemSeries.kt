package com.kaedenoki.moviecorner.data.series.model


import com.google.gson.annotations.SerializedName

data class ItemSeries(
    @SerializedName("banner")
    val banner: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("quality")
    val quality: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null
)