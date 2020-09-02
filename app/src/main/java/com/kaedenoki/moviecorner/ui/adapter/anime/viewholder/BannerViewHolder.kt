package com.kaedenoki.moviecorner.ui.adapter.anime.viewholder

import android.app.Activity
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.anime.model.ItemAnime
import com.kaedenoki.moviecorner.databinding.ItemBannerSeriesBinding
import com.kaedenoki.moviecorner.databinding.ViewholderCarouselBinding

class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var binding = ViewholderCarouselBinding.bind(itemView)
    fun bindView(activity: Activity, data:List<Any>) {
        binding.apply {
            carouselView.apply {
                size = data.size
                enableSnapping(true)
                resource = R.layout.item_banner_anime
                autoPlay = true
                scaleOnScroll = true
                spacing = 0
                hideIndicator(false)
                indicatorSelectedColor = ContextCompat.getColor(context, R.color.activeIndicatorBanner)
                indicatorUnselectedColor = ContextCompat.getColor(context, R.color.inactiveIndicatorBanner)
                indicatorAnimationType = IndicatorAnimationType.THIN_WORM
                carouselOffset = OffsetType.START
                setCarouselViewListener { view, position ->
                    val binding = ItemBannerSeriesBinding.bind(view)
                    val itemData = data[position] as ItemAnime
                    binding.apply {
                        ivItemBanner.load(itemData.thumb)
                    }
                }
                show()
            }
        }
    }
}