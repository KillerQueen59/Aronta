package com.ojanbelajar.aronta.data.source.remote.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FarmerResponse(
    var user: String = "",

    var _id: String = "",

    var name: String = "",

    var email: String = "",

    var phoneNumber: String = "",

    var address: String = "",

    var picture: String = ""
): Parcelable