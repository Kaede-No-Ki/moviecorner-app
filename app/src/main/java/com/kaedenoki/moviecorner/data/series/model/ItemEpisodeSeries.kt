package com.kaedenoki.moviecorner.data.series.model

data class ItemEpisodeSeries(
    var `data`: List<Data>,
    var season: Int
) {
    data class Data(
        var episode: String,
        var url: String,
        var id: String
    )
}