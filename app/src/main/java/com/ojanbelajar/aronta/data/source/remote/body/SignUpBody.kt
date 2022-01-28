package com.ojanbelajar.aronta.data.source.remote.body

data class SignUpBody (
    val email: String,
    val password: String,
    val role: String,
    val farmer: Farmer
)