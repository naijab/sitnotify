package com.naijab.sitnotify.network

import com.naijab.sitnotify.newsmenu.model.NewsModel
import retrofit2.Call
import retrofit2.http.Query


interface NotifyAPI {

    fun getNews(@Query("key") key: String)
    : Call<NewsModel>;

}
