package com.naijab.sitnotify.newsmenu.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CalendarModel(
        @SerializedName("_id") val id: String? = null,
        @SerializedName("title") val title: String? = null,
        @SerializedName("description") val description: String? = null,
        @SerializedName("date") val date: String? = null,
        @SerializedName("link") val link: String? = null,
        @SerializedName("created_date") val createdDate: String? = null,
        @SerializedName("__v") val v: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(title)
        writeString(description)
        writeString(date)
        writeString(link)
        writeString(createdDate)
        writeString(v)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CalendarModel> = object : Parcelable.Creator<CalendarModel> {
            override fun createFromParcel(source: Parcel): CalendarModel = CalendarModel(source)
            override fun newArray(size: Int): Array<CalendarModel?> = arrayOfNulls(size)
        }
    }
}



