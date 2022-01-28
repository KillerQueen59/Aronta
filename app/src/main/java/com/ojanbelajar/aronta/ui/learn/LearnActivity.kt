package com.ojanbelajar.aronta.ui.learn

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.databinding.ActivityLearnBinding
import com.ojanbelajar.aronta.ui.home.HomeViewModel
import com.ojanbelajar.aronta.utils.SessionManagement
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnActivity: AppCompatActivity() {

    lateinit var binding: ActivityLearnBinding
    lateinit var model: LearnDetailViewModel
    lateinit var session: SessionManagement
    lateinit var adapterLearn: LearnDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(LearnDetailViewModel::class.java)
        session = SessionManagement(applicationContext)
        adapterLearn = LearnDetailAdapter(this)
        getData()


        binding.rvLearn.layoutManager = LinearLayoutManager(this)
        binding.rvLearn.adapter = adapterLearn
    }

    fun getData(){
        model.getLesson(session.token).observe(this,{ result ->
            if (result != null){
                when(result){
                    is Resource.Loading ->{
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        if (result.data!=null){
                            adapterLearn.setData(result.data)
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }

        })
    }
}