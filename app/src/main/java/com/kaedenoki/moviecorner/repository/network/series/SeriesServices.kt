package com.kaedenoki.moviecorner.repository.network.series

import android.util.Log
import com.kaedenoki.moviecorner.data.series.response.HomeSeriesResponse
import com.kaedenoki.moviecorner.repository.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesServices {

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
}