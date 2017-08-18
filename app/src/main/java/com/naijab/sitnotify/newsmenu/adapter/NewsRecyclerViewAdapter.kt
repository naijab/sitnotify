package com.naijab.sitnotify.newsmenu.adapter

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

class NewsRecyclerViewAdapter(val newsList: ArrayList<NewsModel>,
                              val listener: (NewsModel) -> Unit)
    : RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.news_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bindItems(item, listener)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    private fun getItem(position: Int): NewsModel{
        return newsList[position]
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(news: NewsModel, listener: (NewsModel) -> Unit) = with(itemView){

            itemView.imageNews.visibility = View.VISIBLE

            setTitle(news.title)
            setProgram(news.program)
            setImage(news.image)
            setOnClickListener { listener(news) }
        }

        fun setTitle(title:String?){
            itemView.titleNews.text = title
        }

        fun setProgram(program:String?){
            itemView.programNews.text = program
        }

        fun setImage(url:String?){
            if(url?.length == 0){
                itemView.imageNews.visibility = View.GONE
                Log.i("NewsAdapter","image gone")
            }else{
                itemView.imageNews.visibility = View.VISIBLE
                Log.i("NewsAdapter","image visible")
                Glide.with(itemView.context)
                        .load(url)
                        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                        .into(itemView.imageNews)
            }
        }
    }
}