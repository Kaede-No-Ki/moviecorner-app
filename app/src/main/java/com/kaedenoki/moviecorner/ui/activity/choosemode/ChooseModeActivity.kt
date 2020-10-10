package com.kaedenoki.moviecorner.ui.activity.choosemode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.kaedenoki.moviecorner.databinding.ActivityChooseModeBinding
import com.kaedenoki.moviecorner.helper.Const.MODE_ANIME
import com.kaedenoki.moviecorner.helper.Const.MODE_SERIES
import com.kaedenoki.moviecorner.helper.Helpers
import com.kaedenoki.moviecorner.repository.local.HawkStore
import com.kaedenoki.moviecorner.ui.activity.main.MainActivity

class ChooseModeActivity : AppCompatActivity() {
    lateinit var binding: ActivityChooseModeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ivAnime.load(Helpers.imgMode[0])
            ivSeries.load(Helpers.imgMode[1])
            cardAnime.setOnClickListener {
                HawkStore.saveMode(this@ChooseModeActivity, MODE_ANIME)
                val intent = Intent(this@ChooseModeActivity, MainActivity::class.java)
                startActivity(intent)
            }
            cardSeries.setOnClickListener {
                HawkStore.saveMode(this@ChooseModeActivity, MODE_SERIES)
                val intent = Intent(this@ChooseModeActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}