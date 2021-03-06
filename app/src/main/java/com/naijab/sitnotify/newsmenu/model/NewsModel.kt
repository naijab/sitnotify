package com.naijab.sitnotify.newsmenu.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class NewsModel(
        @SerializedName("_id") val id: String? = null,
        @SerializedName("title") val title: String ? = null,
        @SerializedName("image") val image: String ? = null,
        @SerializedName("description") val description: String ? = null,
        @SerializedName("category") val category: String ? = null,
        @SerializedName("level") val level: String ? = null,
        @SerializedName("program") val program: String ? = null,
        @SerializedName("link") val link: String ? = null,
        @SerializedName("attached_file") val attachedFile: String ? = null,
        @SerializedName("created_date") val createdDate: String ? = null,
        @SerializedName("edited_date") val editedDate: String? = null,
        @SerializedName("__v") val v: String ? = null,
        @SerializedName("is_important") val isImportant: Boolean
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(title)
        writeString(image)
        writeString(description)
        writeString(category)
        writeString(level)
        writeString(program)
        writeString(link)
        writeString(attachedFile)
        writeString(createdDate)
        writeString(editedDate)
        writeString(v)
        writeInt((if (isImportant) 1 else 0))
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<NewsModel> = object : Parcelable.Creator<NewsModel> {
            override fun createFromParcel(source: Parcel): NewsModel = NewsModel(source)
            override fun newArray(size: Int): Array<NewsModel?> = arrayOfNulls(size)
        }
    }
}


