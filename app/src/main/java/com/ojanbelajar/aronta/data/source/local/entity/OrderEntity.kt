package com.ojanbelajar.aronta.data.source.local.entity

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


@Parcelize
data class OrderEntity (
    var _id: String = "",
    var type: String = "",
    var workingHours: Int = 0,
    var price: Int = 0,
    var status: Int = 0,
    var farmer: FarmerEntity = FarmerEntity(),
    var worker: BuruhEntity = BuruhEntity(),
    var orderDate: String = ""
): Parcelable