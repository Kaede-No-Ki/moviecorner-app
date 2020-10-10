package com.kaedenoki.moviecorner.data.anime.response

import com.google.gson.annotations.SerializedName

data class AnimeEpisodeResponse(

	@field:SerializedName("link_stream")
	val linkStream: String? = null,

	@field:SerializedName("baseUrl")
	val baseUrl: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("quality")
	val quality: Quality? = null
)

data class HighQuality(

	@field:SerializedName("download_links")
	val downloadLinks: List<DownloadLinksItem?>? = null,

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("quality")
	val quality: String? = null
)

data class Quality(

	@field:SerializedName("high_quality")
	val highQuality: HighQuality? = null,

	@field:SerializedName("medium_quality")
	val mediumQuality: MediumQuality? = null,

	@field:SerializedName("low_quality")
	val lowQuality: LowQuality? = null
)

data class DownloadLinksItem(

	@field:SerializedName("host")
	val host: String? = null,

	@field:SerializedName("link")
	val link: String? = null
)

data class LowQuality(

	@field:SerializedName("download_links")
	val downloadLinks: List<DownloadLinksItem?>? = null,

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("quality")
	val quality: String? = null
)

data class MediumQuality(

	@field:SerializedName("download_links")
	val downloadLinks: List<DownloadLinksItem?>? = null,

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("quality")
	val quality: String? = null
)
