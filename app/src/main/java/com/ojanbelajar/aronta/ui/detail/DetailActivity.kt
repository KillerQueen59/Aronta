package com.ojanbelajar.aronta.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ojanbelajar.aronta.data.source.local.entity.BuruhEntity
import com.ojanbelajar.aronta.databinding.ActivityDetailBinding
import android.content.Intent

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import com.ojanbelajar.aronta.R
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.data.source.remote.body.OrderBody
import com.ojanbelajar.aronta.ui.home.HomeActivity
import com.ojanbelajar.aronta.utils.SessionManagement
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton
import java.lang.Exception
import java.net.URLEncoder

@AndroidEntryPoint
class DetailActivity: AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    lateinit var data: BuruhEntity
    lateinit var tipe: String
    lateinit var model: DetailViewModel

    lateinit var session: SessionManagement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(DetailViewModel::class.java)

        session = SessionManagement(this)
        data = intent.getParcelableExtra("buruhs") ?: BuruhEntity()
        tipe = intent.getStringExtra("tipe") ?: ""
        populateData(data)
        onClick()
    }

    fun onClick(){
        binding.btnCall.setOnClickListener {
            sendWa()
        }
        binding.btnOrder.setOnClickListener {
            if (binding.edtWorkhour.text.isNotEmpty()) order()
            else{
                binding.edtWorkhour.error = "Isikan Jam Kerja"
            }
        }
    }

    fun order(){
        val body = OrderBody(data.id,tipe,binding.edtWorkhour.text.toString().toInt(),data.harga.toInt()*binding.edtWorkhour.text.toString().toInt())
        model.order(body,session.token).observe(this,{ result ->
            if (result != null){
                when(result){
                    is Resource.Loading ->{
                    }
                    is Resource.Success -> {
                        alert("Berhasil Memesan") {
                            yesButton {
                                startActivity<HomeActivity>()
                                finish()
                            }
                        }.show()

                    }
                    is Resource.Error -> {
                        alert(result.message.toString()) {
                            yesButton {

                            }
                        }.show()
                    }
                }
            }

        })
    }


    fun populateData(data: BuruhEntity){
        binding.txtDetailName.text = data.nama
        binding.txtRating.text = "${data.rating}/5"
        binding.txtSkill.text = data.keahlian
        binding.txtExperience.text = "Pengalaman Jam Kerja : ${data.workhour} Jam"
        binding.txtPrice.text = "Biaya: ${data.harga}/jam"
        when(data.nama){
            "Bapak Hadi" -> Glide.with(this).load(R.drawable.picture1).into(binding.imgDetail)
            "Bapak Bayu " -> Glide.with(this).load(R.drawable.picture3).into(binding.imgDetail)
            "Bapak Kusuma" -> Glide.with(this).load(R.drawable.picture4).into(binding.imgDetail)
            "Bapak Wahyu" -> Glide.with(this).load(R.drawable.picture12).into(binding.imgDetail)
            "Bapak Indra" -> Glide.with(this).load(R.drawable.picture22).into(binding.imgDetail)
            "Ibu Ira" -> Glide.with(this).load(R.drawable.picture5).into(binding.imgDetail)
            "Bapak Kasman" -> Glide.with(this).load(R.drawable.picture7).into(binding.imgDetail)
            "Bapak Iwan" -> Glide.with(this).load(R.drawable.picture8).into(binding.imgDetail)
            "Ibu Maya" -> Glide.with(this).load(R.drawable.picture9).into(binding.imgDetail)
        }    }

    fun sendWa(){
        val i = Intent(Intent.ACTION_VIEW)
        try {
            val url =
                "https://api.whatsapp.com/send?phone=" + data.telepon + "&text=" + URLEncoder.encode(
                    "Selamat Siang Pak/Ibu ${data.nama}, saya ingin menggunakan jasa Bapak/Ibu",
                    "UTF-8"
                )
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)
            startActivity(i)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}