package com.kaedenoki.moviecorner.repository.network.anime

import android.util.Log
import com.kaedenoki.moviecorner.data.anime.response.HomeAnimeResponse
import com.kaedenoki.moviecorner.repository.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeServices {

    fun getHome(callback: (HomeAnimeResponse) -> Unit) {
        RetrofitInstance.getAnimeClient().getHome().enqueue(
            object : Callback<HomeAnimeResponse> {
                override fun onFailure(call: Call<HomeAnimeResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<HomeAnimeResponse>,
                    seriesResponse: Response<HomeAnimeResponse>
                ) {
                    Log.d("TAG", "onResponse: ${seriesResponse.body()}")
                    callback(seriesResponse.body()!!)
                }
            }
        )
    }
}