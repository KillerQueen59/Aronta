package com.ojanbelajar.aronta.ui.order

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ojanbelajar.aronta.data.source.local.entity.LearnEntity
import com.ojanbelajar.aronta.data.source.local.entity.OrderEntity
import com.ojanbelajar.aronta.data.source.remote.response.OrderResponse
import com.ojanbelajar.aronta.databinding.CardLearnBinding
import com.ojanbelajar.aronta.databinding.CardOrderBinding
import com.ojanbelajar.aronta.ui.learn.LearnDetailActivity
import org.jetbrains.anko.startActivity
import java.util.ArrayList

class OrderAdapter  ( private val context: Context)
    : RecyclerView.Adapter<OrderAdapterViewHolder>() {

    private var listData = ArrayList<OrderEntity>()

    fun setData(newListData: List<OrderEntity>) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapterViewHolder {
        val viewHolder =  OrderAdapterViewHolder(CardOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (listData[position].status == 3){
                parent.context.startActivity<AfterOrderActivity>("order" to listData[position])
            } else {
                parent.context.startActivity<OrderDetailActivity>("order" to listData[position])
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: OrderAdapterViewHolder, position: Int) {
        holder.bind(listData[position],context)
    }

    override fun getItemCount(): Int = listData.size

}

class OrderAdapterViewHolder(private val binding: CardOrderBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(learn: OrderEntity, context: Context){
        binding.txtDate.text = "Tanggal :${learn.orderDate}"
        binding.txtNameWorker.text = "Nama Buruh  :${ learn.worker.nama }"
        binding.txtType.text = "Tipe: ${ learn.type }"
        when(learn.status){
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
    }
}