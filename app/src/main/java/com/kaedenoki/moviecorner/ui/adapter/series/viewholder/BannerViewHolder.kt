package com.kaedenoki.moviecorner.ui.adapter.series.viewholder

import android.app.Activity
import android.util.DisplayMetrics
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.series.model.ItemSeries
import com.kaedenoki.moviecorner.databinding.ItemBannerSeriesBinding
import com.kaedenoki.moviecorner.databinding.ViewholderCarouselBinding

class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var binding = ViewholderCarouselBinding.bind(itemView)
    fun bindView(activity: Activity, data:List<Any>) {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        binding.apply {
            carouselView.apply {
                size = data.size
                enableSnapping(true)
                resource = R.layout.item_banner_series
                autoPlay = true
                scaleOnScroll = true
                spacing = 0
                hideIndicator(false)
                indicatorSelectedColor = ContextCompat.getColor(context, R.color.activeIndicatorBanner)
                indicatorUnselectedColor = ContextCompat.getColor(context, R.color.inactiveIndicatorBanner)
                indicatorAnimationType = IndicatorAnimationType.THIN_WORM
                carouselOffset = OffsetType.CENTER
                setCarouselViewListener { view, position ->
                    val binding = ItemBannerSeriesBinding.bind(view)
                    val itemData = data[position] as ItemSeries
                    binding.apply {
                        cvItemBanner.layoutParams.width = width - 128
                        ivItemBanner.load(itemData.banner)
                    }
                }
                show()
            }
        }
    }
}