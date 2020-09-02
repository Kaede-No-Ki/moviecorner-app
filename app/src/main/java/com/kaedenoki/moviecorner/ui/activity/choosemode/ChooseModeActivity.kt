package com.kaedenoki.moviecorner.ui.activity.choosemode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.kaedenoki.moviecorner.databinding.ActivityChooseModeBinding
import com.kaedenoki.moviecorner.helper.Const.MODE_ANIME
import com.kaedenoki.moviecorner.helper.Const.MODE_SERIES
import com.kaedenoki.moviecorner.repository.local.HawkStore
import com.kaedenoki.moviecorner.ui.activity.main.MainActivity

class ChooseModeActivity : AppCompatActivity() {
    lateinit var binding: ActivityChooseModeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ivAnime.load("https://images2.alphacoders.com/742/thumb-1920-742320.png")
            ivSeries.load("https://assets.st-note.com/production/uploads/images/25980292/picture_pc_1d466b6781c87b2fd537736dc38775f4.jpg")
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