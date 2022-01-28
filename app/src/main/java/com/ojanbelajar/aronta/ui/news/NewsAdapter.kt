package com.ojanbelajar.aronta.ui.news

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ojanbelajar.aronta.R
import com.ojanbelajar.aronta.data.source.local.entity.NewsEntity
import com.ojanbelajar.aronta.databinding.CardNewsBinding
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.ArrayList

class NewsAdapter (private val context: Context)
    : RecyclerView.Adapter<NewsAdapterViewHolder>(){

    private var listData = ArrayList<NewsEntity>()

    fun setData(newListData: List<NewsEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapterViewHolder {
        val viewHolder =  NewsAdapterViewHolder(CardNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            parent.context.startActivity<NewsActivity>("news" to listData[position],"position" to position)
        }
        return viewHolder
    }


    override fun onBindViewHolder(holder: NewsAdapterViewHolder, position: Int) {
        holder.bind(listData[position],context,position)

    }

    override fun getItemCount(): Int = listData.size
}

class NewsAdapterViewHolder(private val binding: CardNewsBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(news: NewsEntity, context: Context, index: Int){
        binding.txtTitleNews.text = news.judul
        when(index){
            0 ->  Glide.with(context).load(R.drawable.berita1).into(binding.imgNews)
            1 ->  Glide.with(context).load(R.drawable.berita2).into(binding.imgNews)
            2 ->  Glide.with(context).load(R.drawable.berita3).into(binding.imgNews)
            3 ->  Glide.with(context).load(R.drawable.berita4).into(binding.imgNews)
            4 ->  Glide.with(context).load(R.drawable.berita5).into(binding.imgNews)
            5 ->  Glide.with(context).load(R.drawable.berita6).into(binding.imgNews)
            6 ->  Glide.with(context).load(R.drawable.berita7).into(binding.imgNews)

        }
    }


}
