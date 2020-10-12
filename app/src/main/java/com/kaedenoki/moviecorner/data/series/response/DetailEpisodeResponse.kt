package com.kaedenoki.moviecorner.data.series.response

data class DetailEpisodeResponse(
    var `data`: Data,
    var message: String,
    var status: Boolean
) {
    data class Data(
        var urlDownload: List<UrlStreaming>,
        var urlStreaming: List<UrlStreaming>
    ) {
        data class UrlDownload(
            var name: String,
            var url: String
        )

        data class UrlStreaming(
            var name: String,
            var url: String
        )
    }
}