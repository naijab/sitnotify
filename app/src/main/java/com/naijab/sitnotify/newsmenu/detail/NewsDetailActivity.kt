package com.naijab.sitnotify.newsmenu.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.naijab.sitnotify.R
import com.naijab.sitnotify.newsmenu.model.NewsModel
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.content_activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    private val NEWS_ITEM = "NewsItem"
    private var news:NewsModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        initView()
        initNewsItem()
        setupView()
    }

    private fun initView(){
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
    }

    private fun initNewsItem() {
        val bundle: Bundle = intent.extras
        news = bundle.getParcelable(NEWS_ITEM)
    }

    private fun setupView() {
        toolbar.title = news?.title
        titleNews.text = news?.title
        detailNews.text = news?.description
        setImage(news?.image)
    }

    private fun setImage(url:String?){
        Glide.with(this)
                .load(url)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(imageNews)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(NEWS_ITEM, news)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        news = savedInstanceState?.getParcelable<NewsModel>(NEWS_ITEM)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
