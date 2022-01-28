package com.ojanbelajar.aronta.ui.order

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ojanbelajar.aronta.data.source.Resource
import com.ojanbelajar.aronta.data.source.local.entity.LearnEntity
import com.ojanbelajar.aronta.data.source.local.entity.OrderEntity
import com.ojanbelajar.aronta.data.source.remote.response.OrderResponse
import com.ojanbelajar.aronta.databinding.ActivityOrderDetailBinding
import com.ojanbelajar.aronta.ui.home.HomeActivity
import com.ojanbelajar.aronta.utils.SessionManagement
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

@AndroidEntryPoint
class OrderDetailActivity: AppCompatActivity() {

    lateinit var binding: ActivityOrderDetailBinding
    lateinit var order: OrderEntity
    lateinit var session: SessionManagement
    lateinit var model: OrderDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = SessionManagement(this)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model =  ViewModelProvider(this).get(OrderDetailViewModel::class.java)
        order = intent.getParcelableExtra("order")!!
        populateData(order)
        if (order.status == 5){
            binding.btnCancel.visibility = View.GONE
        }
        binding.btnCancel.setOnClickListener {
            cancel()
        }
    }

    fun cancel(){
        model.cancel(session.token,order._id).observe(this,{ result ->
            if (result != null){
                when(result){
                    is Resource.Loading ->{
                    }
                    is Resource.Success -> {
                        alert("Berhasil Membatakan") {
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

    fun populateData(order: OrderEntity){
        binding.txtNameFarmer.text = "Nama: ${ order.farmer.nama }"
        binding.txtNoTelepon.text = "Telepon: ${ order.farmer.telepon }"
        binding.txtLokasiLadang.text = "Alamat: ${ order.farmer.alamat }"
        binding.txtNameBuruh.text = "Nama: ${ order.worker.nama }"
        binding.txtNoTeleponBuruh.text = "Telepon: ${ order.worker.telepon }"
        binding.txtPrice.text = "Harga: ${ order.worker.harga }"
        binding.txtTanggalPemesanan.text = "Tanggal: ${ order.orderDate }"
        binding.txtTipeJasa.text = "Tipe Jasa: ${ order.type }"
        binding.txtJamKerja.text = "Jam Kerja: ${ order.workingHours.toString() }"
        binding.txtTotalPrice.text =
            "Total Harga: ${ (order.worker.harga.toInt() * order.workingHours).toString() }"
        when(order.status){
            1->{
                binding.txtStatus.text = "Status: Belum DiTerima oleh Worker"
            }
            2->{
                binding.txtStatus.text = "Status: Sudah DiTerima oleh Worker"
            }
            3->{
                binding.txtStatus.text = "Status: Selesai"
            }
            4->{
                binding.txtStatus.text = "Status: Sudah Dibayar"
            }
            5->{
                binding.txtStatus.text = "Status: Cancel"
            }
        }
        binding.btnOrder.visibility = View.GONE
    }
}