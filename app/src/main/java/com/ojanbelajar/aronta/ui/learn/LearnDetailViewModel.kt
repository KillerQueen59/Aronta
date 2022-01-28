package com.ojanbelajar.aronta.ui.learn

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.aronta.data.source.Repository

class LearnDetailViewModel @ViewModelInject constructor(
    val contentRepository: Repository
) : ViewModel()  {

    fun getLesson(token: String) = contentRepository.getLesson(token).asLiveData()

}