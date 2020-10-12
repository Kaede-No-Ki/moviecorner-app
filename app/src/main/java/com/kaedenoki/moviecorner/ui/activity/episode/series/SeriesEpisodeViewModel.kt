package com.kaedenoki.moviecorner.ui.activity.episode.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaedenoki.moviecorner.data.series.response.DetailEpisodeResponse
import com.kaedenoki.moviecorner.repository.network.series.SeriesServices

class SeriesEpisodeViewModel : ViewModel() {
    private val detailEpisode = MutableLiveData<DetailEpisodeResponse.Data>()
    private val urlStreaming = MutableLiveData<String>()

    fun episode() : LiveData<DetailEpisodeResponse.Data>{
        return detailEpisode
    }

    fun url() : LiveData<String>{
        return urlStreaming
    }

    fun getEpisode(id: String){
        SeriesServices().getDetailEpisode(id){
            detailEpisode.value = it.data
        }
    }

    fun setUrlStreaming(index: Int) {
        val detailEpisodeResponse = detailEpisode.value
        urlStreaming.value = detailEpisodeResponse?.urlStreaming?.get(index)?.url
    }
}