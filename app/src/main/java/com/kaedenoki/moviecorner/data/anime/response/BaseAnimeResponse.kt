package com.kaedenoki.moviecorner.data.anime.response

import com.google.gson.annotations.SerializedName
import com.kaedenoki.moviecorner.data.anime.model.ItemAnime

data class BaseAnimeResponse(
	@field:SerializedName("baseUrl")
	val baseUrl: String? = null,
	@field:SerializedName("animeList")
	val animeList: List<ItemAnime?>? = null,
	@field:SerializedName("status")
	val status: String? = null
)

