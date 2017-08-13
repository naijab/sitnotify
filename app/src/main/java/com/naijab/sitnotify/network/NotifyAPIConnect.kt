package com.naijab.sitnotify.network

import android.icu.util.TimeUnit
import javax.xml.datatype.DatatypeConstants.MINUTES
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


class NotifyAPIConnect {

    private val BASE_URL:String = "http://sitnotify.azurewebsites.net/api/"

    companion object {
        fun newInstance(): NotifyAPIConnect {
            val notifyConnect: NotifyAPIConnect = NotifyAPIConnect()
            return notifyConnect
        }
    }

    fun getConnection(): NotifyAPI {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val notifyAPI:NotifyAPI = retrofit.create(NotifyAPI::class.java)
        return notifyAPI
    }
    
}