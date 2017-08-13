package com.naijab.sitnotify.network

import com.naijab.sitnotify.newsmenu.model.NewsModel
import io.reactivex.Flowable
import retrofit2.http.Query


interface NotifyAPI {

    fun getNews(@Query("key") key: String)
    : Flowable<NewsModel>

}
