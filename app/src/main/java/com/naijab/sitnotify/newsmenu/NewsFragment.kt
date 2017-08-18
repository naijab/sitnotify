package com.naijab.sitnotify.newsmenu

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.naijab.sitnotify.R
import com.naijab.sitnotify.network.NotifyAPIConnect
import com.naijab.sitnotify.newsmenu.adapter.NewsRecyclerViewAdapter
import com.naijab.sitnotify.newsmenu.detail.NewsDetailActivity
import com.naijab.sitnotify.newsmenu.model.NewsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var mNewsArrayList: ArrayList<NewsModel>? = null
    private val NEWS_ITEM = "NewsItem"

    companion object {
        fun newInstance(): NewsFragment {
            val newsFragment: NewsFragment = NewsFragment()
            return newsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNews()
        initView()
    }

    fun initView() {
        initSwipeRefreshLayout()
        initRecyclerView()
    }

    fun initSwipeRefreshLayout() {
        swipeRefresh.setOnRefreshListener(this)
    }

    fun initRecyclerView(){
        newsRecycler.layoutManager = LinearLayoutManager(activity)
        newsRecycler.setHasFixedSize(true)
    }

    fun getNews() {
        swipeRefresh?.isRefreshing = true
        Log.i("NewsFragment", "Call API")
        NotifyAPIConnect().loadNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,this::handleError)
    }

    fun handleResponse(newsList: List<NewsModel>) {
        swipeRefresh?.isRefreshing = false
        mNewsArrayList = ArrayList<NewsModel>(newsList)
        setupRecyclerView(mNewsArrayList as ArrayList<NewsModel>)
        Log.i("NewsFragment","Load API finished")
    }

    fun handleError(error:Throwable){
        swipeRefresh?.isRefreshing = false
        Log.e("NewsFragment",error.message)
    }

    fun setupRecyclerView(newsList: ArrayList<NewsModel>) {
        newsRecycler.adapter = NewsRecyclerViewAdapter(newsList, listener = {
            goToNewsDetail(it)
        })
    }

    fun goToNewsDetail(newsList: NewsModel) {
        val intent = Intent(activity, NewsDetailActivity::class.java)
        intent.putExtra(NEWS_ITEM, newsList)
        startActivity(intent)
    }

    override fun onRefresh() {
        getNews()
        newsRecycler.adapter.notifyDataSetChanged()
        Log.i("NewsFragment","OnRefresh finished")
    }

}
