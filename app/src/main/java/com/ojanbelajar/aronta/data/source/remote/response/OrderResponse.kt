package com.ojanbelajar.aronta.data.source.remote.response

import android.os.Parcelable
import com.ojanbelajar.aronta.data.source.remote.body.Farmer
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderResponse (
    val _id: String,
    val type: String,
    val workingHours: Int,
    val price: Int,
    val status: Int,
    val farmer: Farmer,
    val worker: WorkerResponse,
    val orderDate: String
    ):Parcelable