package com.ojanbelajar.aronta.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.aronta.data.source.Repository

class SearchViewModel @ViewModelInject constructor(
    val contentRepository: Repository
) : ViewModel() {
    fun getWorkerSearch(search: String,token: String) = contentRepository.getWorkerSearch(search,token).asLiveData()

    fun getWorker(token: String)= contentRepository.getWorker(token).asLiveData()

}