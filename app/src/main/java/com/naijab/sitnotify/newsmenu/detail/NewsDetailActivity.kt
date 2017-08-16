package com.naijab.sitnotify.newsmenu.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.naijab.sitnotify.R
import com.naijab.sitnotify.newsmenu.model.NewsModel

class NewsDetailActivity : AppCompatActivity() {

    private val NEWS_ITEM = "NewsItem"
    private var news:NewsModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        initNewsItem()
    }

    private fun initNewsItem() {
        val bundle: Bundle = intent.extras
        news = bundle.getParcelable(NEWS_ITEM)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(NEWS_ITEM, news)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        news = savedInstanceState?.getParcelable<NewsModel>(NEWS_ITEM)
    }
}
