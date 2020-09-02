package com.kaedenoki.moviecorner.data.series.response


import com.google.gson.annotations.SerializedName
import com.kaedenoki.moviecorner.data.series.model.ItemSeries

data class HomeSeriesResponse(
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: Boolean? = null
)

data class Data(
    @SerializedName("boxOffice")
    val boxOffice: List<ItemSeries>? = null,
    @SerializedName("mostViewed")
    val mostViewed: List<ItemSeries>? = null,
    @SerializedName("newMovies")
    val newMovies: List<ItemSeries>? = null,
    @SerializedName("newSeries")
    val newSeries: List<ItemSeries>? = null
)