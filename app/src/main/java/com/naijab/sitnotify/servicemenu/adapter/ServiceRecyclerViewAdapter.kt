package com.naijab.sitnotify.servicemenu.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.naijab.sitnotify.R
import com.naijab.sitnotify.servicemenu.model.ServiceModel
import java.util.ArrayList
import kotlinx.android.synthetic.main.service_list_layout.view.*

class ServiceRecyclerViewAdapter(val serviceList: ArrayList<ServiceModel>,
                                 val listener: (ServiceModel) -> Unit)
    : RecyclerView.Adapter<ServiceRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.service_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bindItems(item, listener)
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }

    private fun getItem(position: Int): ServiceModel {
        return serviceList[position]
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(service: ServiceModel, listener: (ServiceModel) -> Unit) = with(itemView){
            setTitle(service.title)
            setDescription(service.description)
            setOnClickListener { listener(service) }
        }

        fun setTitle(title:String?){
            itemView.titleService.text = title
        }

        fun setDescription(description:String?){
            itemView.descriptionService.text = description
        }
    }
}