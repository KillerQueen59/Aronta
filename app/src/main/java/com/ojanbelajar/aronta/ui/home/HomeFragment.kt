package com.ojanbelajar.aronta.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.data.source.local.entity.FarmerEntity
import com.ojanbelajar.aronta.data.source.local.entity.LearnEntity
import com.ojanbelajar.aronta.data.source.local.entity.NewsEntity
import com.ojanbelajar.aronta.databinding.FragmentHomeBinding
import com.ojanbelajar.aronta.ui.detail.DetailViewModel
import com.ojanbelajar.aronta.ui.learn.LearnActivity
import com.ojanbelajar.aronta.ui.learn.LearnAdapter
import com.ojanbelajar.aronta.ui.learn.LearnDetailActivity
import com.ojanbelajar.aronta.ui.login.LoginViewModel
import com.ojanbelajar.aronta.ui.news.NewsAdapter
import com.ojanbelajar.aronta.ui.today.TodayActivity
import com.ojanbelajar.aronta.utils.SessionManagement
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.yesButton

@AndroidEntryPoint
class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var model: HomeViewModel

    lateinit var session: SessionManagement
    lateinit var adapterNews: NewsAdapter
    lateinit var adapterLearn: LearnAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        adapterNews = NewsAdapter(context!!)
        adapterLearn = LearnAdapter(context!!)
        model = ViewModelProvider(this).get(HomeViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        session = SessionManagement(activity!!.applicationContext)
        getData()
        onClick()
        prepareRecyclerView()
    }

    fun getData(){
        model.getNews(session.token).observe(viewLifecycleOwner,{ result ->
            if (result != null){
                when(result){
                    is Resource.Loading ->{
                    }
                    is Resource.Success -> {
                        if (result.data!=null){
                            adapterNews.setData(result.data)
                        }
                    }
                    is Resource.Error -> {
                        alert("Sesi Berakhir silahkan login kembali") {
                            yesButton {
                                (activity as HomeActivity).logout()
                            }
                        }.show()
                    }
                }
            }
        })
        model.getLesson(session.token).observe(viewLifecycleOwner,{ result ->
            if (result != null){
                when(result){
                    is Resource.Loading ->{
                    }
                    is Resource.Success -> {
                        if (result.data!=null){
                            adapterLearn.setData(result.data)
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }

        })
    }

    fun onClick(){
        binding.lnPlant.setOnClickListener {
            startActivity<TodayActivity>("tipe" to "plant")
        }
        binding.lnHarvest.setOnClickListener {
            startActivity<TodayActivity>("tipe" to "harvest")
        }
        binding.lnCare.setOnClickListener {
            startActivity<TodayActivity>("tipe" to "care")
        }
        binding.seeMoreLearn.setOnClickListener {
            startActivity<LearnActivity>()
        }
    }

    fun prepareRecyclerView(){
        binding.rvLearn.layoutManager = LinearLayoutManager(context)
        binding.rvLearn.adapter = adapterLearn
        binding.rvNews.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rvNews.adapter = adapterNews
    }

}