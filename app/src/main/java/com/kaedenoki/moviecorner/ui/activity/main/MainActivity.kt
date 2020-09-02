package com.kaedenoki.moviecorner.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kaedenoki.moviecorner.R
import com.kaedenoki.moviecorner.databinding.ActivityMainBinding
import com.kaedenoki.moviecorner.ui.fragment.browse.BrowseFragment
import com.kaedenoki.moviecorner.ui.fragment.download.DownloadFragment
import com.kaedenoki.moviecorner.ui.fragment.home.HomeFragment
import com.kaedenoki.moviecorner.ui.fragment.mylist.MyListFragment
import com.kaedenoki.moviecorner.ui.fragment.setting.SettingFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val fragments = listOf(
        HomeFragment(),
        MyListFragment(),
        BrowseFragment(),
        DownloadFragment(),
        SettingFragment()
    )
    var activeFragment = fragments[0]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setRecycler()

    }

    private fun setRecycler() {

        binding.apply {
            supportFragmentManager.beginTransaction()
                .add(frmMain.id, fragments[0], "Home")
                .add(frmMain.id, fragments[1], "MyList").hide(fragments[1])
                .add(frmMain.id, fragments[2], "Browse").hide(fragments[2])
                .add(frmMain.id, fragments[3], "Download").hide(fragments[3])
                .add(frmMain.id, fragments[4], "Setting").hide(fragments[4])
                .commit()

            bottomNav.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.menuHome -> {
                        showFragment(fragments[0])
                    }
                    R.id.menuMyList -> {
                        showFragment(fragments[1])
                    }
                    R.id.menuBrowse -> {
                        showFragment(fragments[2])
                    }
                    R.id.menuDownload -> {
                        showFragment(fragments[3])
                    }
                    else -> {
                        showFragment(fragments[4])
                    }
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
        return true
    }
}