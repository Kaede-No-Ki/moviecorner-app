package com.kaedenoki.moviecorner.ui.adapter.series

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.general.Home
import com.kaedenoki.moviecorner.helper.Const.ITEM_BANNER
import com.kaedenoki.moviecorner.helper.Const.ITEM_DATA
import com.kaedenoki.moviecorner.ui.adapter.general.RecyclerHomeContract
import com.kaedenoki.moviecorner.ui.adapter.series.viewholder.BannerViewHolder
import com.kaedenoki.moviecorner.ui.adapter.general.viewholder.ItemGeneralViewHolder
import com.kaedenoki.moviecorner.ui.adapter.general.viewholder.TitleViewHolder

class RecyclerHomeSeriesAdapter(val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), RecyclerHomeContract {
    var data = Home()
    override fun setHomeData(home: Home){
        this.data = home
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            ITEM_BANNER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_carousel, parent, false)
                BannerViewHolder(view)
            }
            ITEM_DATA -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_itemgeneral, parent, false)
                ItemGeneralViewHolder(
                    view
                )
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
                TitleViewHolder(
                    view
                )
            }
        }

    }

    override fun getItemCount(): Int {
        return data.list?.size?:0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(data.list?.get(position)?.type){
            ITEM_BANNER -> {
                (holder as BannerViewHolder).bindView(activity, data.list?.get(position)?.data as List<Any>)
            }
            ITEM_DATA -> {
                (holder as ItemGeneralViewHolder).bindView(activity, data.list?.get(position)?.data as List<Any>, 1)
            }
            else -> {
                (holder as TitleViewHolder).bindView(activity, data.list?.get(position)?.data as String)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data.list?.get(position)?.type?:0
    }


}