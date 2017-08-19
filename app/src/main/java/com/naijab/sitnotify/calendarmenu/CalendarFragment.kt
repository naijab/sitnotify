package com.naijab.sitnotify.newsmenu


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naijab.sitnotify.R
import com.naijab.sitnotify.calendarmenu.adapter.CalendarRecyclerViewAdapter
import com.naijab.sitnotify.network.NotifyAPIConnect
import com.naijab.sitnotify.newsmenu.model.CalendarModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var mcalendarArrayList: ArrayList<CalendarModel>? = null
    private val CALENDAR_ITEM = "CalendarItem"

    companion object {
        fun newInstance(): CalendarFragment {
            val calendarFragment: CalendarFragment = CalendarFragment()
            return calendarFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCalendar()
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
        calendarRecycler.layoutManager = LinearLayoutManager(activity)
        calendarRecycler.setHasFixedSize(true)
    }

    fun getCalendar() {
        swipeRefresh?.isRefreshing = true
        Log.i("CalendarFragment", "Call API")
        NotifyAPIConnect().loadCalendar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,this::handleError)
    }

    fun handleResponse(calendarList: List<CalendarModel>) {
        swipeRefresh?.isRefreshing = false
        mcalendarArrayList = ArrayList<CalendarModel>(calendarList)
        setupRecyclerView(mcalendarArrayList as ArrayList<CalendarModel>)
        Log.i("CalendarFragment","Load API finished")
    }

    fun handleError(error:Throwable){
        swipeRefresh?.isRefreshing = false
        Log.e("CalendarFragment",error.message)
    }

    fun setupRecyclerView(calendarList: ArrayList<CalendarModel>) {
        calendarRecycler.adapter = CalendarRecyclerViewAdapter(calendarList, listener = {
            goToLink(it)
        })
    }

    fun goToLink(calendarList: CalendarModel) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(calendarList.link)
        startActivity(intent)
    }

    override fun onRefresh() {
        getCalendar()
        calendarRecycler.adapter.notifyDataSetChanged()
        Log.i("CalendarFragment","OnRefresh finished")
    }

}
