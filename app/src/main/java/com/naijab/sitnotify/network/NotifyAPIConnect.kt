package com.naijab.sitnotify.network

import com.naijab.sitnotify.newsmenu.model.CalendarModel
import com.naijab.sitnotify.newsmenu.model.NewsModel
import com.naijab.sitnotify.servicemenu.model.ServiceModel
import io.reactivex.Observable
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class NotifyAPIConnect {

    private val BASE_URL = "http://sitnotify.azurewebsites.net/api/"
    private val SECRETE_KEY = "Dy0XS0KSL2kTCV9wTIrvOAC4S12NFeugR"
    private val service:NotifyAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create<NotifyAPI>(NotifyAPI::class.java)
    }

    fun loadNews(): Observable<List<NewsModel>> {
        return service.getNews(SECRETE_KEY)
    }

    fun loadCalendar(): Observable<List<CalendarModel>> {
        return service.getCalendar(SECRETE_KEY)
    }

    fun loadService(): Observable<List<ServiceModel>> {
        return service.getService(SECRETE_KEY)
    }
}