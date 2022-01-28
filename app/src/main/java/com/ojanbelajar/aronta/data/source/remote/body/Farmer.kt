package com.ojanbelajar.aronta.data.source.remote.body

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Farmer (
    val name: String,
    val phoneNumber: String,
    val address: String
    ):Parcelable