package com.ojanbelajar.aronta.data.source.remote

import com.ojanbelajar.aronta.data.source.remote.body.OrderBody
import com.ojanbelajar.aronta.data.source.remote.body.SignUpBody
import com.ojanbelajar.aronta.data.source.remote.network.ApiResponse
import com.ojanbelajar.aronta.data.source.remote.response.*
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body

interface RemoteDataInterface {

    suspend fun login(body: RequestBody): Flow<ApiResponse<LoginResponse>>

    suspend fun signUp(body: SignUpBody): Flow<ApiResponse<SignUpResponse>>

    suspend fun getWorkerSearch(search: String, token: String): Flow<ApiResponse<WorkerFullResponse>>

    suspend fun getWorker(token: String): Flow<ApiResponse<WorkerFullResponse>>

    suspend fun getNews(token: String): Flow<ApiResponse<NewsResponse>>

    suspend fun getLesson(token: String): Flow<ApiResponse<LessonResponse>>

    suspend fun order(body: OrderBody,token: String): Flow<ApiResponse<SignUpResponse>>

    suspend fun getOrder(token: String,id: String): Flow<ApiResponse<OrderFullResponse>>

    suspend fun cancel(token: String,id: String): Flow<ApiResponse<SignUpResponse>>

    suspend fun rate(token: String, id: String, rate: Float): Flow<ApiResponse<SignUpResponse>>

    suspend fun picture(token:String, picture: MultipartBody.Part): Flow<ApiResponse<SignUpResponse>>

}