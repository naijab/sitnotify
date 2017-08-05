package com.naijab.sitnotify.newsmenu


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naijab.sitnotify.R

class MoreFragment : Fragment() {

    companion object {
        fun newInstance(): MoreFragment {
            val moreFragment: MoreFragment = MoreFragment()
            return moreFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_news, container, false)
    }

}
