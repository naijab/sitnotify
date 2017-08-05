package com.naijab.sitnotify.newsmenu


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naijab.sitnotify.R

class CalendarFragment : Fragment() {

    companion object {
        fun newInstance(): CalendarFragment {
            val calendarFragment: CalendarFragment = CalendarFragment()
            return calendarFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_news, container, false)
    }

}
