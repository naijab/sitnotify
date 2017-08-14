package com.naijab.sitnotify.newsmenu.model

import com.google.gson.annotations.SerializedName

data class NewsModel (
    @SerializedName("_id") val id:String,
    @SerializedName("title") val title:String,
    @SerializedName("image") val image:String,
    @SerializedName("description") val description:String,
    @SerializedName("category") val category:String,
    @SerializedName("level") val level:String,
    @SerializedName("program") val program:String,
    @SerializedName("link") val link:String,
    @SerializedName("attached_file") val attachedFile:String,
    @SerializedName("created_date") val createdDate:String,
    @SerializedName("edited_date") val editedDate:String,
    @SerializedName("__v") val v:Int,
    @SerializedName("is_important") val isImportant:Boolean
)

