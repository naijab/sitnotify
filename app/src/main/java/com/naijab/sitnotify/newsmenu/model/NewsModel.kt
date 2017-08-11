package com.naijab.sitnotify.newsmenu.model

import com.google.gson.annotations.SerializedName

data class NewsModel (
        @SerializedName("_id")
        val id:String,
        val title:String,
        val image:String,
        val description:String,
        val category:String,
        val level:String,
        val program:String,
        val link:String,
        val attached_file:String,
        val is_important:String
)