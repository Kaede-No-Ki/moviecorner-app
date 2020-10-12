package com.kaedenoki.moviecorner.ui.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaedenoki.moviecorner.data.anime.response.ResponseDetailAnime
import com.kaedenoki.moviecorner.data.series.response.DetailSeries
import com.kaedenoki.moviecorner.helper.Const.MODE_ANIME
import com.kaedenoki.moviecorner.repository.network.anime.AnimeServices
import com.kaedenoki.moviecorner.repository.network.series.SeriesServices

class DetailAnimeViewModel : ViewModel() {

    val anime = MutableLiveData<ResponseDetailAnime>()
    val series = MutableLiveData<DetailSeries>()

    fun getAnime(): LiveData<ResponseDetailAnime> {
        return anime
    }

    fun getSeries(): LiveData<DetailSeries>{
        return series
    }

    fun initData(id: String, mode: String) {
        if (mode == MODE_ANIME){
            AnimeServices().getDetailAnime(id) {
                anime.value = it
            }
        } else {
            SeriesServices().getDetailSeries(id){
                series.value = it
            }
        }

    }
}