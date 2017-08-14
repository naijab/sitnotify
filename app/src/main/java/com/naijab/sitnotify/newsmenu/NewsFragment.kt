package com.naijab.sitnotify.newsmenu

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.naijab.sitnotify.R
import com.naijab.sitnotify.network.NotifyAPIConnect
import com.naijab.sitnotify.newsmenu.adapter.NewsRecyclerViewAdapter
import com.naijab.sitnotify.newsmenu.model.NewsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    private var mNewsArrayList: ArrayList<NewsModel>? = null
    private var mAdapter: NewsRecyclerViewAdapter? = null

    companion object {
        fun newInstance(): NewsFragment {
            val newsFragment: NewsFragment = NewsFragment()
            return newsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        getNews()
        return inflater!!.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    fun setRecyclerView(){
        newsRecycler.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
    }

    fun getNews() {
        Log.e("News Fragment:", "Call")
        NotifyAPIConnect().loadNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,this::handleError)
    }

    fun handleResponse(newsList: List<NewsModel>) {
        mNewsArrayList = ArrayList<NewsModel>(newsList)
        setupRecyclerView(mNewsArrayList as ArrayList<NewsModel>)
    }

    fun handleError(error:Throwable){
        Log.e("News Fragment",error.message)
    }

    fun setupRecyclerView(newsList: ArrayList<NewsModel>) {
        mAdapter = NewsRecyclerViewAdapter(newsList)
        newsRecycler.adapter = mAdapter
    }

}
