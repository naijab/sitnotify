package com.naijab.sitnotify


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NewsFragment : Fragment() {

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

}
