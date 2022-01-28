package com.ojanbelajar.aronta.ui.learn

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ojanbelajar.aronta.R
import com.ojanbelajar.aronta.data.source.local.entity.LearnEntity
import com.ojanbelajar.aronta.databinding.CardLearnBinding
import org.jetbrains.anko.startActivity
import java.util.ArrayList

class LearnDetailAdapter ( private val context: Context)
    : RecyclerView.Adapter<LearnDetailAdapterViewHolder>() {

    private var listData = ArrayList<LearnEntity>()

    fun setData(newListData: List<LearnEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnDetailAdapterViewHolder {
        val viewHolder =  LearnDetailAdapterViewHolder(CardLearnBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            parent.context.startActivity<LearnDetailActivity>("learn" to listData[position], "position" to position)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: LearnDetailAdapterViewHolder, position: Int) {
        holder.bind(listData[position],context,position)
    }

    override fun getItemCount(): Int = listData.size

}

class LearnDetailAdapterViewHolder(private val binding: CardLearnBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(learn: LearnEntity, context: Context,index: Int){
        binding.titleLearn.text = learn.judul
        binding.titleDesc.text = learn.konten
        when(index){
            0 ->  Glide.with(context).load(R.drawable.learn1).into(binding.imgLearn)
            1 ->  Glide.with(context).load(R.drawable.learn2).into(binding.imgLearn)
            2 ->  Glide.with(context).load(R.drawable.learn3).into(binding.imgLearn)
            3 ->  Glide.with(context).load(R.drawable.learn4).into(binding.imgLearn)
            4 ->  Glide.with(context).load(R.drawable.learn5).into(binding.imgLearn)
        }
    }

}