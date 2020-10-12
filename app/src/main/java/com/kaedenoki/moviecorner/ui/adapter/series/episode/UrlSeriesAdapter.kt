package com.kaedenoki.moviecorner.ui.adapter.series.episode

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.series.response.DetailEpisodeResponse
import com.kaedenoki.moviecorner.databinding.ItemUrlStreamingBinding

class UrlSeriesAdapter(
    val urlStreaming: List<DetailEpisodeResponse.Data.UrlStreaming>,
    val callback: (Int) -> Unit
) :
    RecyclerView.Adapter<UrlSeriesAdapter.ViewHolder>() {
    var active = -1

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(active: Int, position: Int, callback: (Int) -> Unit) {

            val binding = ItemUrlStreamingBinding.bind(itemView)
            binding.apply {
                if (active == position) {
                    btnUrlStreaming.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                itemView.context,
                                R.color.colorPrimary
                            )
                        )
                    btnUrlStreaming.setTextColor(
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                itemView.context,
                                android.R.color.white
                            )
                        )
                    )
                } else {
                    btnUrlStreaming.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                itemView.context,
                                android.R.color.white
                            )
                        )
                    btnUrlStreaming.setTextColor(
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                itemView.context,
                                android.R.color.black
                            )
                        )
                    )
                }
                btnUrlStreaming.text = "Server ${position + 1}"
                btnUrlStreaming.setOnClickListener {
                    callback(position)

                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_url_streaming, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(active, position) {
            active = it
            notifyDataSetChanged()
            callback(it)
        }
    }

    override fun getItemCount(): Int = urlStreaming.size
}