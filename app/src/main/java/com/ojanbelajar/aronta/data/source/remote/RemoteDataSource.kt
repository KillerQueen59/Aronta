package com.ojanbelajar.aronta.data.source.remote

import android.util.Log
import com.ojanbelajar.aronta.data.source.remote.body.OrderBody
import com.ojanbelajar.aronta.data.source.remote.body.SignUpBody
import com.ojanbelajar.aronta.data.source.remote.network.ApiResponse
import com.ojanbelajar.aronta.data.source.remote.network.ApiService
import com.ojanbelajar.aronta.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.http.Body
import javax.inject.Inject


class RemoteDataSource  @Inject constructor(private val apiService: ApiService): RemoteDataInterface{

    override suspend fun login(body: RequestBody): Flow<ApiResponse<LoginResponse>> {
        return flow {
            try {
                val response = apiService.login(body)
                if (!response.equals(null)) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }

    override suspend fun signUp(body: SignUpBody): Flow<ApiResponse<SignUpResponse>> {
        return flow {
            try {
                val response = apiService.signUp(body)
                if (!response.equals(null)) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }

    override suspend fun getWorkerSearch(
        search: String,
        token: String
    ): Flow<ApiResponse<WorkerFullResponse>> {
        return flow {
            try {
                val response = apiService.getWorkerSearch(search,token)
                if (!response.equals(null)) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }

    override suspend fun getWorker(token: String): Flow<ApiResponse<WorkerFullResponse>> {
        return flow {
            try {
                val response = apiService.getWorker(token)
                if (!response.equals(null)) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }

    override suspend fun getNews(token: String): Flow<ApiResponse<NewsResponse>> {
        return flow {
            try {
                val response = apiService.getNews("Bearer $token")
                if (!response.equals(null)){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getLesson(token: String): Flow<ApiResponse<LessonResponse>> {
        return flow {
            try {
                val response = apiService.getLesson("Bearer $token")
                if (!response.equals(null)){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun order(body: OrderBody, token: String): Flow<ApiResponse<SignUpResponse>>  {
        return flow {
            try {
                val response = apiService.order(body,token)
                if (!response.equals(null)) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }

    override suspend fun getOrder(token: String,id: String): Flow<ApiResponse<OrderFullResponse>> {
        return flow {
            try {
                val response = apiService.getOrder(token,id)
                if (!response.equals(null)) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }

    override suspend fun cancel(token: String, id: String): Flow<ApiResponse<SignUpResponse>> {
        return flow {
            try {
                val response = apiService.cancel(token,id,"cancel")
                if (!response.equals(null)) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }

    override suspend fun rate(
        token: String,
        id: String,
        rate: Float
    ): Flow<ApiResponse<SignUpResponse>> {
        return flow {
            try {
                val url = "https://apifarmer.herokuapp.com/api/v1/order/rate/${id}"
                val response = apiService.rate(token,url,rate)
                if (!response.equals(null)) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }

    override suspend fun picture(
        token: String,
        picture: MultipartBody.Part
    ): Flow<ApiResponse<SignUpResponse>> {
        return flow {
            try {
                val response = apiService.picture(token,picture)
                if (!response.equals(null)) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }


}