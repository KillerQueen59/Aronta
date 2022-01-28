package com.ojanbelajar.aronta.data.source

import com.ojanbelajar.aronta.data.source.local.entity.*
import com.ojanbelajar.aronta.data.source.remote.body.OrderBody
import com.ojanbelajar.aronta.data.source.remote.body.SignUpBody
import com.ojanbelajar.aronta.data.source.remote.response.LoginResponse
import com.ojanbelajar.aronta.data.source.remote.response.NewsResponse
import com.ojanbelajar.aronta.data.source.remote.response.OrderResponse
import com.ojanbelajar.aronta.data.source.remote.response.SignUpResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body

interface Repository {

    fun login(body: RequestBody): Flow<Resource<LoginResponse>>

    fun signUp(body: SignUpBody): Flow<Resource<SignUpResponse>>

    fun order(body: OrderBody,token: String): Flow<Resource<SignUpResponse>>

    fun getWorkerSearch(search: String,token: String): Flow<Resource<List<BuruhEntity>>>

    fun getWorker(token: String): Flow<Resource<List<BuruhEntity>>>

    fun getNews(token: String): Flow<Resource<List<NewsEntity>>>

    fun getLesson(token: String): Flow<Resource<List<LearnEntity>>>

    fun getOrder(token: String,id: String): Flow<Resource<List<OrderEntity>>>

    fun cancel(token: String,id: String): Flow<Resource<SignUpResponse>>

    fun rate(token: String,id: String,rate: Float): Flow<Resource<SignUpResponse>>

    fun picture(token: String,picture: MultipartBody.Part): Flow<Resource<SignUpResponse>>

}