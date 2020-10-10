package com.kaedenoki.moviecorner.ui.activity.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaedenoki.moviecorner.data.anime.response.AnimeEpisodeResponse
import com.kaedenoki.moviecorner.repository.network.anime.AnimeServices
import kotlinx.coroutines.launch

class AnimeEpisodeViewModel : ViewModel() {

    private val episode = MutableLiveData<AnimeEpisodeResponse>()

    fun getEpisode(): LiveData<AnimeEpisodeResponse> {
        return episode
    }

    fun getAnimeEpisode(id: String) = viewModelScope.launch {
        AnimeServices().getEpisodeAnime(id) { episode.value = it }
    }
}
