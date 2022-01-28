package com.ojanbelajar.aronta.ui.order

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.aronta.data.source.Repository

class OrderDetailViewModel @ViewModelInject
constructor(
    val contentRepository: Repository
) : ViewModel() {

    fun getOrder(token: String,id: String) = contentRepository.getOrder(token,id).asLiveData()

    fun cancel(token: String,id: String) = contentRepository.cancel(token,id).asLiveData()

    fun rate(token: String,id: String, rate: Float) = contentRepository.rate(token,id,rate).asLiveData()
}