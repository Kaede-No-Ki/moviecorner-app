package com.kaedenoki.moviecorner.data.series.response

import com.kaedenoki.moviecorner.data.series.model.ItemEpisodeSeries

data class DetailSeries(
    var `data`: Data,
    var message: String,
    var status: Boolean
) {
    data class Data(
        var banner: String,
        var descriptions: Descriptions,
        var episodes: List<ItemEpisodeSeries>,
        var id: String,
        var thumbnail: String,
        var title: String
    ) {
        data class Descriptions(
            var actors: String? = "-",
            var country: String? = "-",
            var genre: String? = null,
            var release: String? = null,
            var studio: String? = "-",
            var tvstatus: String? = null,
            var views: String? = "-",
            var tmdb: String? = null,
            var duration: String? = null,
            var director: String? = "-"
        )
    }
}