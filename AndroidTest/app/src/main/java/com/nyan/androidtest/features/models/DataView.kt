package com.nyan.androidtest.features.models

import android.os.Parcel
import com.nyan.androidtest.core.platform.KParcelable
import com.nyan.androidtest.core.platform.parcelableCreator

data class DataView(val id: Int, val title: String, val subTitle: String, val content: String) : KParcelable {
    companion object {
        @JvmField
        val CREATOR = parcelableCreator(
                ::DataView)
    }

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(title)
            writeString(subTitle)
            writeString(content)
        }
    }
}