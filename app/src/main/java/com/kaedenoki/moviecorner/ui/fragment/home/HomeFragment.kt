package com.kaedenoki.moviecorner.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.data.general.Home
import com.kaedenoki.moviecorner.databinding.FragmentHomeBinding
import com.kaedenoki.moviecorner.helper.Const.MODE_ANIME
import com.kaedenoki.moviecorner.helper.Const.MODE_SERIES
import com.kaedenoki.moviecorner.repository.local.HawkStore
import com.kaedenoki.moviecorner.ui.adapter.anime.RecyclerHomeAnimeAdapter
import com.kaedenoki.moviecorner.ui.adapter.general.RecyclerHomeContract
import com.kaedenoki.moviecorner.ui.adapter.series.RecyclerHomeSeriesAdapter

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var homeAdapter : RecyclerHomeContract
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var skeleton: Skeleton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        initSkeleton()
        getDataHome()
        homeViewModel.home().observe(viewLifecycleOwner, Observer {
            setData(it)
        })
        homeViewModel.loading().observe(viewLifecycleOwner, Observer {
            if(it){
                skeleton.showSkeleton()
            }else {
                skeleton.showOriginal()
            }
        })
    }

    private fun initSkeleton() {
        skeleton = binding.rvHome.applySkeleton(R.layout.skeleton_home_series,1)
        skeleton.showShimmer = true
        skeleton.maskColor = ContextCompat.getColor(requireContext(), R.color.maskColor)
        skeleton.shimmerColor = ContextCompat.getColor(requireContext(), R.color.shimmerColor)
    }

    private fun getDataHome() {
        val mode = HawkStore.getMode(requireContext())
        if (mode == MODE_SERIES) {
            homeViewModel.getHomeSeries()
        } else {
            homeViewModel.getHomeAnime()
        }
    }

    private fun initRecylerView() {
        val mode = HawkStore.getMode(requireContext())
        if(mode == MODE_SERIES){
            binding.apply{
                homeAdapter =
                    RecyclerHomeSeriesAdapter(
                        requireActivity()
                    )
                rvHome.adapter = homeAdapter as RecyclerHomeSeriesAdapter
                rvHome.layoutManager = LinearLayoutManager(requireContext())
            }
        } else if(mode == MODE_ANIME){
            binding.apply{
                homeAdapter =
                    RecyclerHomeAnimeAdapter(
                        requireActivity()
                    )
                rvHome.adapter = homeAdapter as RecyclerHomeAnimeAdapter
                rvHome.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun setData(it: Home?) {
        initRecylerView()
        homeAdapter.setHomeData(it!!)
    }
}