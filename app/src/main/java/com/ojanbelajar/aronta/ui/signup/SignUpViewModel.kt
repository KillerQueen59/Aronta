package com.ojanbelajar.aronta.ui.signup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.aronta.data.source.Repository
import com.ojanbelajar.aronta.data.source.remote.body.SignUpBody
import okhttp3.RequestBody

class SignUpViewModel  @ViewModelInject constructor(
    val contentRepository: Repository
) : ViewModel() {
    fun signUp(body: SignUpBody) = contentRepository.signUp(body).asLiveData()

}