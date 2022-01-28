package com.ojanbelajar.aronta.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.aronta.data.source.Repository
import okhttp3.RequestBody
import retrofit2.http.Body

class LoginViewModel @ViewModelInject constructor(
    val contentRepository: Repository
) : ViewModel() {

    fun login(body: RequestBody) = contentRepository.login(body).asLiveData()
}