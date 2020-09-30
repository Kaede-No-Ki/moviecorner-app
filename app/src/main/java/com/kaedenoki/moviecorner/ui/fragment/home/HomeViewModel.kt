package com.kaedenoki.moviecorner.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaedenoki.moviecorner.data.general.Home
import com.kaedenoki.moviecorner.data.general.ItemHome
import com.kaedenoki.moviecorner.helper.Const.ITEM_BANNER
import com.kaedenoki.moviecorner.helper.Const.ITEM_DATA
import com.kaedenoki.moviecorner.helper.Const.ITEM_TITLE
import com.kaedenoki.moviecorner.repository.network.anime.AnimeServices
import com.kaedenoki.moviecorner.repository.network.series.SeriesServices
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val home = MutableLiveData<Home>().apply {
        value = Home()
    }
    private val isLoading = MutableLiveData<Boolean>().apply {
        value = true
    }

    fun getHomeSeries() = viewModelScope.launch {
        isLoading.value = true
        SeriesServices().getHome {

            val mostViewed = it.data!!.mostViewed
            val newSeries = it.data.newSeries
            val boxOffice = it.data.boxOffice
            val newMovies = it.data.newMovies
            val dataHome = Home(
                listOf(
                    ItemHome(
                        ITEM_BANNER,
                        mostViewed as Any
                    ),
                    ItemHome(
                        ITEM_TITLE,
                        "New Series"
                    ),
                    ItemHome(
                        ITEM_DATA,
                        newSeries as Any
                    ),
                    ItemHome(
                        ITEM_TITLE,
                        "Box Office"
                    ),
                    ItemHome(
                        ITEM_DATA,
                        boxOffice as Any
                    ),
                    ItemHome(
                        ITEM_TITLE,
                        "New Movies"
                    ),
                    ItemHome(
                        ITEM_DATA,
                        newMovies as Any
                    )
                )
            )
            isLoading.value = false
            home.value = dataHome
        }
    }

    fun getHomeAnime() = viewModelScope.launch {
        isLoading.value = true
        AnimeServices().getHome {

            isLoading.value = false
            val onGoing = it.home?.onGoing
            val complete = it.home?.complete

            val dataHome = Home(
                listOf(
                    ItemHome(ITEM_BANNER, complete as Any),
                    ItemHome(ITEM_TITLE, "On Going"),
                    ItemHome(ITEM_DATA, onGoing as Any),
                    ItemHome(ITEM_TITLE, "Selesai"),
                    ItemHome(ITEM_DATA, complete as Any)
                )
            )
            home.value = dataHome
        }
    }


    fun home(): LiveData<Home> {
        return home
    }

    fun loading(): LiveData<Boolean> {
        return isLoading
    }
}