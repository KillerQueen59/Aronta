package com.ojanbelajar.aronta.ui.today

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ojanbelajar.aronta.R
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.data.source.local.entity.BuruhEntity
import com.ojanbelajar.aronta.databinding.ActivityTodayBinding
import com.ojanbelajar.aronta.utils.SessionManagement
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

@AndroidEntryPoint
class TodayActivity: AppCompatActivity() {

    lateinit var binding: ActivityTodayBinding
    var type: String = ""
    lateinit var session: SessionManagement
    lateinit var model: TodayViewModel
    lateinit var adapter: TodayAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model =  ViewModelProvider(this).get(TodayViewModel::class.java)
        session = SessionManagement(this)
        type = intent.getStringExtra("tipe") ?: ""
        adapter = TodayAdapter(this,type)
        prepareRV()
        initial(type)
    }

    fun initial(type: String){
        getDataBuruh()
        when(type){
            "plant" -> {
                Glide.with(this).load(R.drawable.plant).into(binding.imgToday)
                binding.txtToday.text = "Menanam"
            }
            "harvest" -> {
                Glide.with(this).load(R.drawable.harvest).into(binding.imgToday)
                binding.txtToday.text = "Memanen"
            }
            "care" -> {
                Glide.with(this).load(R.drawable.care).into(binding.imgToday)
                binding.txtToday.text = "Merawat"
            }
        }

    }

    fun getDataBuruh(){
            model.getWorker(session.token).observe(this,{ result ->
            if (result != null){
                when(result){
                    is Resource.Loading ->{
                        binding.lnProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.lnProgressBar.visibility = View.GONE
                        if (result.data!=null){
                            var dats = ArrayList<BuruhEntity>()
                            for (d in result.data){
                                if (d.tipe.contains(type)){
                                    var buruh = BuruhEntity(d.id,d.nama,d.email,d.telepon,d.harga,d.keahlian,d.workhour,d.tipe,d.rating,d.image)
                                    dats.add(buruh)
                                }
                            }
                            adapter.setData(dats)
                        }
                    }
                    is Resource.Error -> {
                        binding.lnProgressBar.visibility = View.GONE
                        alert(result.message.toString()) {
                            yesButton {}
                        }.show()
                    }
                }
            }

        })
    }
    fun prepareRV(){
        binding.rvSearch.setHasFixedSize(true)
        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        binding.rvSearch.adapter = adapter
    }

}