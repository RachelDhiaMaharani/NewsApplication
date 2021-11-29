package com.rachel.newsapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachel.newsapplication.databinding.NewsItemBinding
import com.rachel.newsapplication.model.ArticlesItem

class NewsAdapter (var  context : Context, var listNews : List<ArticlesItem?>?): RecyclerView.Adapter<NewsAdapter. NewsViewHolder>() {

    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    inner class NewsViewHolder (var itemBinding: NewsItemBinding) : RecyclerView.ViewHolder(itemBinding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
       val itemNewsItemBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder (itemNewsItemBinding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
       val news = listNews!![position]
        holder.itemBinding.apply {
            tvTitleItems.text = news?.title
            tvDateItem.text = news?.publishedAt
            tvDurationItem.text = news?.author
        }
        Glide.with(context).load(news?.urlToImage).centerCrop().into(holder.itemBinding.ivItemNews)

        holder.itemView.setOnClickListener {}
    }

    override fun getItemCount(): Int = listNews!!.size
}

interface OnItemClickCallback {
    fun onItemClicked(news: ArticlesItem)

}
