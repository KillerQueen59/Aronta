package com.ojanbelajar.aronta.ui.today

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ojanbelajar.aronta.R
import com.ojanbelajar.aronta.data.source.local.entity.BuruhEntity
import com.ojanbelajar.aronta.databinding.CardSearchBinding
import com.ojanbelajar.aronta.ui.detail.DetailActivity
import com.ojanbelajar.aronta.ui.order.OrderDetailActivity
import org.jetbrains.anko.startActivity
import java.util.ArrayList

class TodayAdapter (
    private val context: Context,private val type: String)
    : RecyclerView.Adapter<TodayAdapterViewHolder>()
    {

        private var listData = ArrayList<BuruhEntity>()

        fun setData(newListData: List<BuruhEntity>?) {
            if (newListData == null) return
            listData.clear()
            listData.addAll(newListData)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayAdapterViewHolder {
            val viewHolder = TodayAdapterViewHolder(
                CardSearchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            viewHolder.itemView.setOnClickListener {
                val position = viewHolder.adapterPosition
                parent.context.startActivity<DetailActivity>("buruhs" to listData[position],"tipe" to type)
            }
            return viewHolder
        }

        override fun onBindViewHolder(holder: TodayAdapterViewHolder, position: Int) {
            if (listData[position].tipe.contains(type)){
                holder.bind(listData[position], context)
            }

        }

        override fun getItemCount(): Int = listData.size

    }

    class TodayAdapterViewHolder(private val binding: CardSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(buruh: BuruhEntity, context: Context) {
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