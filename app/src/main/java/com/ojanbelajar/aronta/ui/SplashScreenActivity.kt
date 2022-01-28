package com.ojanbelajar.aronta.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ojanbelajar.aronta.databinding.ActivitySplashscreenBinding
import com.ojanbelajar.aronta.ui.login.LoginActivity
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity: AppCompatActivity() {

    lateinit var binding: ActivitySplashscreenBinding
    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timer.schedule(3000) {
            startActivity<LoginActivity>()
            finish()
        }
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }
}