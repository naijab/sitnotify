package com.naijab.sitnotify.servicemenu.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ServiceModel(
        @SerializedName("_id") val id: String? = null,
        @SerializedName("title") val title: String? = null,
        @SerializedName("description") val description: String? = null,
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
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(title)
        writeString(description)
        writeString(link)
        writeString(createdDate)
        writeString(v)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ServiceModel> = object : Parcelable.Creator<ServiceModel> {
            override fun createFromParcel(source: Parcel): ServiceModel = ServiceModel(source)
            override fun newArray(size: Int): Array<ServiceModel?> = arrayOfNulls(size)
        }
    }
}


