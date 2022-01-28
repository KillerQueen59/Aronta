package com.ojanbelajar.aronta.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.aronta.data.source.Repository
import com.ojanbelajar.aronta.data.source.remote.body.OrderBody

class DetailViewModel @ViewModelInject constructor(
    val contentRepository: Repository
) : ViewModel() {
    fun order(body: OrderBody,token: String) = contentRepository.order(body,token).asLiveData()
}