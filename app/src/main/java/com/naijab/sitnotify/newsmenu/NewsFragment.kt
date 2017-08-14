package com.naijab.sitnotify.newsmenu


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naijab.sitnotify.R
import com.naijab.sitnotify.network.NotifyAPIConnect
import com.naijab.sitnotify.newsmenu.model.NewsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsFragment : Fragment() {

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

    fun getNews() {
        Log.e("News Fragment:", "Call")
        NotifyAPIConnect().loadNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { news ->
                            Log.e("News Fragment:", news.get(0).title)
                        },
                        { error ->
                            Log.e("News Fragment:", error.message)
                        }
                )
    }

}
