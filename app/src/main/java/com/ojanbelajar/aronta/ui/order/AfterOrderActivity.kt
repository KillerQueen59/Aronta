package com.ojanbelajar.aronta.ui.order

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.data.source.local.entity.OrderEntity
import com.ojanbelajar.aronta.databinding.AfterOrderDetailBinding
import com.ojanbelajar.aronta.utils.SessionManagement
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

@AndroidEntryPoint
class AfterOrderActivity: AppCompatActivity() {

    lateinit var binding: AfterOrderDetailBinding
    lateinit var session: SessionManagement
    lateinit var model: OrderDetailViewModel
    lateinit var order: OrderEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = SessionManagement(this)
        binding = AfterOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model =  ViewModelProvider(this).get(OrderDetailViewModel::class.java)
        order = intent.getParcelableExtra("order")!!
        populateData(order)
        binding.btnFinish.setOnClickListener {
            if (binding.reviewTutorRating.rating == 0f){
                toast("Rating terlebih dahulu")
            } else {
                sendRate(binding.reviewTutorRating.rating)
            }
        }
    }

    fun populateData(order: OrderEntity){
        binding.txtNameBuruh.text = "Nama: ${ order.worker.nama }"
        binding.txtNoTeleponBuruh.text = "Telepon: ${ order.worker.telepon }"
        binding.txtPrice.text = "Harga: ${ order.worker.harga }/Jam"
        binding.txtTanggalPemesanan.text = "Tanggal: ${ order.orderDate }"
        binding.txtTipeJasa.text = "Tipe Jasa: ${ order.type }"
        binding.txtJamKerja.text = "Jam Kerja: ${ order.workingHours.toString() }"
        binding.txtTotalPrice.text = "Total Harga: ${order.price * order.workingHours}"
    }

    fun sendRate(rate: Float){
        model.rate(session.token,order._id,rate).observe(this,{ result ->
            if (result != null){
                when(result){
                    is Resource.Loading ->{
                    }
                    is Resource.Success -> {
                        alert("Berhasil Memberikan Rating") {
                            yesButton {
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
}