package com.kaedenoki.moviecorner.ui.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaedenoki.moviecorner.data.anime.response.ResponseDetailAnime
import com.kaedenoki.moviecorner.repository.network.anime.AnimeServices

class DetailAnimeViewModel : ViewModel() {

    val anime = MutableLiveData<ResponseDetailAnime>()

    fun getAnime(): LiveData<ResponseDetailAnime> {
        return anime
    }

    fun initData(id: String) {
        AnimeServices().getDetailAnime(id) {
            anime.value = it
        }
    }
}