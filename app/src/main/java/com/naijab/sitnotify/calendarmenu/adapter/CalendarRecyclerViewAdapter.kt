package com.naijab.sitnotify.calendarmenu.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naijab.sitnotify.R
import com.naijab.sitnotify.newsmenu.model.CalendarModel
import kotlinx.android.synthetic.main.calendar_list_layout.view.*
import java.util.*

class CalendarRecyclerViewAdapter(val calendarList: ArrayList<CalendarModel>,
                                  val listener: (CalendarModel) -> Unit)
    : RecyclerView.Adapter<CalendarRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.calendar_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bindItems(item, listener)
    }

    override fun getItemCount(): Int {
        return calendarList.size
    }

    private fun getItem(position: Int): CalendarModel {
        return calendarList[position]
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(calendar: CalendarModel, listener: (CalendarModel) -> Unit) = with(itemView) {

            itemView.descriptionCalendar.visibility = View.VISIBLE

            setTitle(calendar.title)
            setDescription(calendar.description)
            setDate(calendar.date)
            setOnClickListener { listener(calendar) }
        }

        fun setTitle(title: String?) {
            itemView.titleCalendar.text = title
        }

        fun setDescription(description: String?) {
            if(description?.length == 0){
                itemView.descriptionCalendar.visibility = View.GONE
                Log.i("CalendarAdapter","description gone")
            }else{
                itemView.descriptionCalendar.visibility = View.VISIBLE
                Log.i("CalendarAdapter","description visible")
                itemView.descriptionCalendar.text = description
            }
        }

        fun setDate(date: String?) {
            itemView.date.text = date
        }
    }
}