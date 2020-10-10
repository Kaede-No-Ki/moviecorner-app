package com.kaedenoki.moviecorner.data.anime.response

import com.google.gson.annotations.SerializedName
import com.kaedenoki.moviecorner.data.anime.model.ItemEpisodeAnime

data class ResponseDetailAnime(

	@field:SerializedName("studio")
	val studio: String? = null,

	@field:SerializedName("genre_list")
	val genreList: List<GenreListItem?>? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("synopsis")
	val synopsis: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("japanase")
	val japanase: String? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("score")
	val score: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("episode_list")
	val episodeList: List<ItemEpisodeAnime?>? = null,

	@field:SerializedName("producer")
	val producer: String? = null,

	@field:SerializedName("total_episode")
	val totalEpisode: String? = null,

	@field:SerializedName("anime_id")
	val animeId: String? = null,

	@field:SerializedName("batch_link")
	val batchLink: BatchLink? = null,

	@field:SerializedName("status")
	val status: String? = null
)


data class BatchLink(

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class GenreListItem(

	@field:SerializedName("genre_name")
	val genreName: String? = null,

	@field:SerializedName("genre_link")
	val genreLink: String? = null,

	@field:SerializedName("genre_id")
	val genreId: String? = null
)
