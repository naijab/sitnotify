package com.naijab.sitnotify.network

import com.naijab.sitnotify.newsmenu.model.CalendarModel
import com.naijab.sitnotify.newsmenu.model.NewsModel
import com.naijab.sitnotify.servicemenu.model.ServiceModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface NotifyAPI {

    @GET("news")
    fun getNews(@Query("key") key: String)
    : Observable<List<NewsModel>>

    @GET("calendar")
    fun getCalendar(@Query("key") key: String)
    : Observable<List<CalendarModel>>

    @GET("service")
    fun getService(@Query("key") key: String)
            : Observable<List<ServiceModel>>

}
