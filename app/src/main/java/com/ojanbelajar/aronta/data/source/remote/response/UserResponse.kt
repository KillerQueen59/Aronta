package com.ojanbelajar.aronta.data.source.remote.response

import android.os.Parcelable
import com.ojanbelajar.aronta.data.source.local.entity.FarmerEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    val roles: List<String>,
    val id: String,
    val email: String,
    val worker: String,
    val farmer: FarmerResponse
):Parcelable
