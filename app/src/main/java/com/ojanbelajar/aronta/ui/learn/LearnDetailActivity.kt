package com.ojanbelajar.aronta.ui.learn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ojanbelajar.aronta.R
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.data.source.local.entity.LearnEntity
import com.ojanbelajar.aronta.data.source.local.entity.NewsEntity
import com.ojanbelajar.aronta.databinding.ActivityLearnDetailBinding
import com.ojanbelajar.aronta.ui.home.HomeViewModel
import com.ojanbelajar.aronta.utils.SessionManagement

class LearnDetailActivity: AppCompatActivity() {

    lateinit var binding: ActivityLearnDetailBinding
    lateinit var learn: LearnEntity
    private var position: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        learn = intent.getParcelableExtra("learn") ?: LearnEntity()
        position = intent.getIntExtra("position",0)
        populateData(learn)

    }
    fun populateData(learn: LearnEntity){
        binding.txtTitleLearn.text = learn.judul
        binding.txtDetailLearn.text = learn.konten
        when(position){
            0 ->  Glide.with(this).load(R.drawable.learn1).into(binding.imgLearn)
            1 ->  Glide.with(this).load(R.drawable.learn2).into(binding.imgLearn)
            2 ->  Glide.with(this).load(R.drawable.learn3).into(binding.imgLearn)
            3 ->  Glide.with(this).load(R.drawable.learn4).into(binding.imgLearn)
            4 ->  Glide.with(this).load(R.drawable.learn5).into(binding.imgLearn)
        }
    }


}