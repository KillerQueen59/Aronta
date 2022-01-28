package com.ojanbelajar.aronta.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.aronta.data.source.Repository

class HomeViewModel @ViewModelInject constructor(
    val contentRepository: Repository
) : ViewModel() {

    fun getNews(token: String) = contentRepository.getNews(token).asLiveData()

    fun getLesson(token: String) = contentRepository.getLesson(token).asLiveData()
}