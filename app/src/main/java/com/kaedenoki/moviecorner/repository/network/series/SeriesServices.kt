package com.kaedenoki.moviecorner.repository.network.series

import android.util.Log
import com.kaedenoki.moviecorner.data.series.response.DetailEpisodeResponse
import com.kaedenoki.moviecorner.data.series.response.DetailSeries
import com.kaedenoki.moviecorner.data.series.response.HomeSeriesResponse
import com.kaedenoki.moviecorner.data.series.response.Ping
import com.kaedenoki.moviecorner.repository.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesServices {

    fun ping(callback: (Ping) -> Unit){
        RetrofitInstance.getSeriesClient().ping().enqueue(
            object : Callback<Ping> {
                override fun onResponse(call: Call<Ping>, response: Response<Ping>) {
                    callback(Ping())
                }

                override fun onFailure(call: Call<Ping>, t: Throwable) {
                    callback(Ping())
                }

            }
        )
    }

    fun getHome(callback: (HomeSeriesResponse) -> Unit) {
        RetrofitInstance.getSeriesClient().getHome().enqueue(
            object : Callback<HomeSeriesResponse> {
                override fun onFailure(call: Call<HomeSeriesResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<HomeSeriesResponse>,
                    seriesResponse: Response<HomeSeriesResponse>
                ) {
                    Log.d("TAG", "onResponse: ${seriesResponse.body()}")
                    callback(seriesResponse.body()!!)
                }
            }
        )
    }

    fun getDetailSeries(id: String, callback: (DetailSeries) -> Unit){
        RetrofitInstance.getSeriesClient().getDetailSeries(id).enqueue(
            object: Callback<DetailSeries>{
                override fun onResponse(
                    call: Call<DetailSeries>,
                    response: Response<DetailSeries>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<DetailSeries>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    fun getDetailEpisode(id:String, callback: (DetailEpisodeResponse) -> Unit){
        RetrofitInstance.getSeriesClient().getEpisode(id).enqueue(
            object : Callback<DetailEpisodeResponse> {
                override fun onResponse(
                    call: Call<DetailEpisodeResponse>,
                    response: Response<DetailEpisodeResponse>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<DetailEpisodeResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
}