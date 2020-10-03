package com.kaedenoki.moviecorner.repository.network.anime

import android.util.Log
import com.kaedenoki.moviecorner.data.anime.response.BaseAnimeResponse
import com.kaedenoki.moviecorner.data.anime.response.HomeAnimeResponse
import com.kaedenoki.moviecorner.data.anime.response.ResponseDetailAnime
import com.kaedenoki.moviecorner.repository.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeServices {

    fun getHome(callback: (HomeAnimeResponse) -> Unit) {
        RetrofitInstance.getAnimeClient().getHome().enqueue(
            object : Callback<HomeAnimeResponse> {
                override fun onFailure(call: Call<HomeAnimeResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
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

    fun getComplete(page: Int?, callback: (BaseAnimeResponse) -> Unit) {

        RetrofitInstance.getAnimeClient().getCompleteAnime(page ?: 1).enqueue(
            object : Callback<BaseAnimeResponse> {
                override fun onResponse(
                    call: Call<BaseAnimeResponse>,
                    response: Response<BaseAnimeResponse>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<BaseAnimeResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getDetailAnime(id: String?, callback: (ResponseDetailAnime) -> Unit){
        RetrofitInstance.getAnimeClient().getDetailAnime(id).enqueue(
            object : Callback<ResponseDetailAnime> {
                override fun onResponse(
                    call: Call<ResponseDetailAnime>,
                    response: Response<ResponseDetailAnime>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<ResponseDetailAnime>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            }
        )
    }

}