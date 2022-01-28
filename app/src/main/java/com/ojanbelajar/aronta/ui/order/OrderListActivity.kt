package com.ojanbelajar.aronta.ui.order

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.data.source.local.entity.LearnEntity
import com.ojanbelajar.aronta.databinding.ActivityLearnDetailBinding
import com.ojanbelajar.aronta.databinding.ActivityListOrderBinding
import com.ojanbelajar.aronta.ui.learn.LearnDetailViewModel
import com.ojanbelajar.aronta.utils.SessionManagement
import com.ojanbelajar.aronta.utils.SessionManagement.Companion.KEY_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderListActivity : AppCompatActivity() {

    lateinit var binding: ActivityListOrderBinding
    lateinit var learn: LearnEntity
    lateinit var session: SessionManagement
    lateinit var model: OrderDetailViewModel
    lateinit var adapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        session = SessionManagement(this)
        adapter = OrderAdapter(this)
        getData()
        model =  ViewModelProvider(this).get(OrderDetailViewModel::class.java)

        binding.rvOrder.layoutManager = LinearLayoutManager(this)
        binding.rvOrder.adapter = adapter
    }


    private fun getData(){
        model.getOrder(session.token, session.user[KEY_ID].toString()).observe(this,{ result ->
            if (result != null){
                when(result){
                    is Resource.Loading ->{
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        if (result.data!=null){
                            adapter.setData(result.data)
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