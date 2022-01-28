package com.ojanbelajar.aronta.ui.news

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ojanbelajar.aronta.R
import com.ojanbelajar.aronta.data.source.local.entity.NewsEntity
import com.ojanbelajar.aronta.databinding.ActivityNewsBinding
import org.jetbrains.anko.toast

class NewsActivity: AppCompatActivity() {

    lateinit var binding: ActivityNewsBinding
    lateinit var news: NewsEntity
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        news = intent.getParcelableExtra("news") ?: NewsEntity()
        position = intent.getIntExtra("position",0)
        populateData(news)

    }

    fun populateData(news: NewsEntity){
        binding.txtTitleNews.text = news.judul
        binding.txtDetailNews.text = news.konten
        when(position){
            0 ->  Glide.with(this).load(R.drawable.berita1).into(binding.imgNews)
            1 ->  Glide.with(this).load(R.drawable.berita2).into(binding.imgNews)
            2 ->  Glide.with(this).load(R.drawable.berita3).into(binding.imgNews)
            3 ->  Glide.with(this).load(R.drawable.berita4).into(binding.imgNews)
            4 ->  Glide.with(this).load(R.drawable.berita5).into(binding.imgNews)
            5 ->  Glide.with(this).load(R.drawable.berita6).into(binding.imgNews)
            6 ->  Glide.with(this).load(R.drawable.berita7).into(binding.imgNews)

        }    }
}