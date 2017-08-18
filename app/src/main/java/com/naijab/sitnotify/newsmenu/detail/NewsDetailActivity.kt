package com.naijab.sitnotify.newsmenu.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.naijab.sitnotify.R
import com.naijab.sitnotify.newsmenu.model.NewsModel
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.content_activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    private val NEWS_ITEM = "NewsItem"
    private var news: NewsModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        initView()
        initNewsItem()
        setupView()
    }

    private fun initView() {
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
        setStatusView()
        setImage(news?.image)
        setFab(news?.attachedFile)
    }

    private fun setStatusView() {
        imageNews.visibility = View.VISIBLE
        fab.visibility = View.VISIBLE
    }

    private fun setImage(url: String?) {
        if (url?.length == 0) {
            imageNews.visibility = View.GONE
            Log.i("NewsDetail", "image gone")
        } else {
            imageNews.visibility = View.VISIBLE
            Log.i("NewsDetail", "image visible")
            Glide.with(this)
                    .load(url)
                    .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .into(imageNews)
        }
    }

    private fun setFab(url: String?){
        if (url?.length == 0) {
            fab.visibility = View.GONE
            Log.i("NewsDetail", "fab gone")
        }else {
            fab.visibility = View.VISIBLE
            Log.i("NewsDetail", "fab visible")
            fab.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
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
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
