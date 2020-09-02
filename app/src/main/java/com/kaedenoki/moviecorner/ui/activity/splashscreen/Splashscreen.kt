package com.kaedenoki.moviecorner.ui.activity.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kaedenoki.moviecorner.databinding.ActivitySplashscreenBinding
import com.kaedenoki.moviecorner.ui.activity.choosemode.ChooseModeActivity

class Splashscreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this, ChooseModeActivity::class.java))
        },3000)
    }
}