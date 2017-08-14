package com.naijab.sitnotify.newsmenu.adapter

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.naijab.sitnotify.R
import com.naijab.sitnotify.newsmenu.model.NewsModel
import java.util.ArrayList
import kotlinx.android.synthetic.main.news_list_layout.view.*

class NewsRecyclerViewAdapter(val newsList: ArrayList<NewsModel>) : RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.news_list_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItems(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(news: NewsModel){
            itemView.titleNews.text = news.title
            Glide.with(itemView.context)
                    .load(news.image)
                    .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .into(itemView.imageNews)
        }
    }
}