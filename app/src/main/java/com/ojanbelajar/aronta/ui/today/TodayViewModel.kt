package com.ojanbelajar.aronta.ui.today

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.aronta.data.source.Repository

class TodayViewModel  @ViewModelInject constructor(
    val contentRepository: Repository
) : ViewModel() {
    fun getWorker(token: String)= contentRepository.getWorker(token).asLiveData()
}