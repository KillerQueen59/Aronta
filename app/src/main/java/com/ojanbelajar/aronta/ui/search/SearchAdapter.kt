package com.ojanbelajar.aronta.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ojanbelajar.aronta.R
import com.ojanbelajar.aronta.data.source.local.entity.BuruhEntity
import com.ojanbelajar.aronta.data.source.local.entity.NewsEntity
import com.ojanbelajar.aronta.databinding.CardSearchBinding
import com.ojanbelajar.aronta.ui.detail.DetailActivity
import com.ojanbelajar.aronta.ui.order.OrderDetailActivity
import org.jetbrains.anko.startActivity
import java.util.ArrayList

class SearchAdapter ( private val context: Context)
    : RecyclerView.Adapter<SearchAdapterViewHolder>() {

        private var listData = ArrayList<BuruhEntity>()

    fun setData(newListData: List<BuruhEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder {
        val viewHolder =  SearchAdapterViewHolder(CardSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            parent.context.startActivity<DetailActivity>("buruhs" to listData[position])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        holder.bind(listData[position],context)
    }

    override fun getItemCount(): Int = listData.size

}

class SearchAdapterViewHolder(private val binding: CardSearchBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(buruh: BuruhEntity,context: Context){
        binding.txtNameSearch.text = buruh.nama
        binding.txtPriceSearch.text = buruh.harga
        binding.txtRating.text = buruh.rating
        when(buruh.nama){
            "Bapak Hadi" -> Glide.with(context).load(R.drawable.picture1).into(binding.imgSearch)
            "Bapak Bayu " -> Glide.with(context).load(R.drawable.picture3).into(binding.imgSearch)
            "Bapak Kusuma" -> Glide.with(context).load(R.drawable.picture4).into(binding.imgSearch)
            "Bapak Wahyu" -> Glide.with(context).load(R.drawable.picture12).into(binding.imgSearch)
            "Bapak Indra" -> Glide.with(context).load(R.drawable.picture22).into(binding.imgSearch)
            "Ibu Ira" -> Glide.with(context).load(R.drawable.picture5).into(binding.imgSearch)
            "Bapak Kasman" -> Glide.with(context).load(R.drawable.picture7).into(binding.imgSearch)
            "Bapak Iwan" -> Glide.with(context).load(R.drawable.picture8).into(binding.imgSearch)
            "Ibu Maya" -> Glide.with(context).load(R.drawable.picture9).into(binding.imgSearch)
        }
    }

}
