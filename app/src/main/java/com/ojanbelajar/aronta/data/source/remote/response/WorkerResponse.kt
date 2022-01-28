package com.ojanbelajar.aronta.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkerResponse (
    val _id: String,
    val phoneNumber: String,
    val hourlyPrice: Int,
    val skill: String,
    val totalWorkingHours: Int,
    val type: List<String>,
    val rating: String,
    val name: String,
    val user: UserResponse,
    val picture: String
    ):Parcelable