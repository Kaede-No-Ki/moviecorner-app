package com.kaedenoki.moviecorner.ui.adapter.general.viewholder

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kaedenoki.moviecorner.databinding.ItemTitleBinding

class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var binding = ItemTitleBinding.bind(itemView)
    fun bindView(activity: Activity, title: String) {
        binding.tvItemTitle.text = title
    }
}