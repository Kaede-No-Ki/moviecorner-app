package com.kaedenoki.moviecorner.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaedenoki.moviecorner.data.general.Home
import com.kaedenoki.moviecorner.data.general.ItemHome
import com.kaedenoki.moviecorner.helper.Const.ITEM_BANNER
import com.kaedenoki.moviecorner.helper.Const.ITEM_DATA
import com.kaedenoki.moviecorner.helper.Const.ITEM_TITLE
import com.kaedenoki.moviecorner.repository.network.anime.AnimeServices
import com.kaedenoki.moviecorner.repository.network.series.SeriesServices

class HomeViewModel : ViewModel() {
    private val home = MutableLiveData<Home>().apply {
        value = Home()
    }
    private val isLoading = MutableLiveData<Boolean>().apply {
        value = true
    }

    fun getHomeSeries() {
        isLoading.value = true
        SeriesServices().getHome {

            val mostViewed = it.data!!.mostViewed
            val newSeries = it.data.newSeries
            val boxOffice = it.data.boxOffice
            val newMovies = it.data.newMovies
            val home = Home(
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
            this.home.value = home
        }
    }

    fun getHomeAnime() {
        isLoading.value = true
        AnimeServices().getHome {
            this.isLoading.value = false
            val onGoing = it.home?.onGoing
            val complete = it.home?.complete

            val home = Home(
                listOf(
                    ItemHome(ITEM_BANNER, complete as Any),
                    ItemHome(ITEM_TITLE, "On Going"),
                    ItemHome(ITEM_DATA, onGoing as Any)
                )
            )
            this.home.value = home
        }
    }


    fun home(): LiveData<Home> {
        return home
    }

    fun loading(): LiveData<Boolean>{
        return isLoading
    }
}