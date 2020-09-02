package com.kaedenoki.moviecorner.ui.adapter.general.viewholder

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaedenoki.moviecorner.databinding.ViewholderItemgeneralBinding
import com.kaedenoki.moviecorner.helper.Const
import com.kaedenoki.moviecorner.repository.local.HawkStore
import com.kaedenoki.moviecorner.ui.adapter.anime.RecyclerItemAnime
import com.kaedenoki.moviecorner.ui.adapter.series.RecyclerItemSeries

class ItemGeneralViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ViewholderItemgeneralBinding.bind(itemView)

    fun bindView(activity: Activity, data: List<Any>) {
        binding.apply {
            val mode = HawkStore.getMode(activity)
            val adapter = if (mode == Const.MODE_SERIES) {
                RecyclerItemSeries(data)
            }else {
                RecyclerItemAnime(data)
            }
            rvItemData.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rvItemData.adapter = adapter
        }
    }
}