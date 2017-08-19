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
import com.naijab.sitnotify.network.NotifyAPIConnect
import com.naijab.sitnotify.servicemenu.adapter.ServiceRecyclerViewAdapter
import com.naijab.sitnotify.servicemenu.model.ServiceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_service.*

class ServiceFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var mServiceArrayList: ArrayList<ServiceModel>? = null
    private val SERVICE_ITEM = "ServiceItem"

    companion object {
        fun newInstance(): ServiceFragment {
            val serviceFragment: ServiceFragment = ServiceFragment()
            return serviceFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_service, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getService()
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
        serviceRecycler.layoutManager = LinearLayoutManager(activity)
        serviceRecycler.setHasFixedSize(true)
    }

    fun getService() {
        swipeRefresh?.isRefreshing = true
        Log.i("ServiceFragment", "Call API")
        NotifyAPIConnect().loadService()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,this::handleError)
    }

    fun handleResponse(newsList: List<ServiceModel>) {
        swipeRefresh?.isRefreshing = false
        mServiceArrayList = ArrayList<ServiceModel>(newsList)
        setupRecyclerView(mServiceArrayList as ArrayList<ServiceModel>)
        Log.i("ServiceFragment","Load API finished")
    }

    fun handleError(error:Throwable){
        swipeRefresh?.isRefreshing = false
        Log.e("ServiceFragment",error.message)
    }

    fun setupRecyclerView(newsList: ArrayList<ServiceModel>) {
        serviceRecycler.adapter = ServiceRecyclerViewAdapter(newsList, listener = {
            goToLink(it)
        })
    }

    fun goToLink(serviceList: ServiceModel) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(serviceList.link)
        startActivity(intent)
    }

    override fun onRefresh() {
        getService()
        serviceRecycler.adapter.notifyDataSetChanged()
        Log.i("ServiceFragment","OnRefresh finished")
    }

}
