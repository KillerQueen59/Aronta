package com.ojanbelajar.aronta.data.source.remote.body

data class OrderBody (
    val worker: String,
    val type: String,
    val workingHours: Int,
    val price: Int
    )