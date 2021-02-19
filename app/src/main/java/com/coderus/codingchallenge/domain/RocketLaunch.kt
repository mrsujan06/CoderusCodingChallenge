package com.coderus.codingchallenge.domain

import android.os.Parcel
import android.os.Parcelable

data class RocketLaunch(
    val flightNumber: Int,
    val name: String?,
    val dateUTC: String?,
    val details: String?,
    val success: Boolean?,
    val upcoming: Boolean?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(flightNumber)
        parcel.writeString(name)
        parcel.writeString(dateUTC)
        parcel.writeString(details)
        parcel.writeValue(success)
        parcel.writeValue(upcoming)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RocketLaunch> {
        override fun createFromParcel(parcel: Parcel): RocketLaunch {
            return RocketLaunch(parcel)
        }

        override fun newArray(size: Int): Array<RocketLaunch?> {
            return arrayOfNulls(size)
        }
    }
}