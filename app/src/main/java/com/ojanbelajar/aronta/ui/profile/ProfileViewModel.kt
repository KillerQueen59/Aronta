package com.ojanbelajar.aronta.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.aronta.data.source.Repository
import okhttp3.MultipartBody

class ProfileViewModel @ViewModelInject constructor(
    val contentRepository: Repository
) : ViewModel() {
    fun picture(token: String,picture: MultipartBody.Part) = contentRepository.picture(token,picture).asLiveData()
}
