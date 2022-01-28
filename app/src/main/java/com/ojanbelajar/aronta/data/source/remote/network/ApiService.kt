package com.ojanbelajar.aronta.data.source.remote.network

import com.ojanbelajar.aronta.data.source.remote.body.OrderBody
import com.ojanbelajar.aronta.data.source.remote.body.SignUpBody
import com.ojanbelajar.aronta.data.source.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @POST("user/login")
    suspend fun login(@Body body: RequestBody): LoginResponse

    @POST("user/register")
    suspend fun signUp(@Body body: SignUpBody): SignUpResponse

    @GET("news")
    suspend fun getNews(
        @Header("Authorization") authorization: String
    ): NewsResponse

    @GET("worker")
    suspend fun getWorkerSearch(
        @Query("name") name: String,
        @Header("Authorization") authorization: String,
    ): WorkerFullResponse

    @GET("worker")
    suspend fun getWorker(
        @Header("Authorization") authorization: String,
    ): WorkerFullResponse

    @GET("lesson")
    suspend fun getLesson(
        @Header("Authorization") authorization: String
    ): LessonResponse

    @POST("order")
    suspend fun order(
        @Body body: OrderBody,
        @Header("Authorization") authorization: String
    ): SignUpResponse

    @GET("order")
    suspend fun getOrder(
        @Header("Authorization") authorization: String,
        @Query("farmer") idFarmer: String
    ): OrderFullResponse

    @PUT("order/status/{idOrder}")
    suspend fun cancel(
        @Header("Authorization") authorization: String,
        @Path("idOrder") id: String,
        @Query("action") action: String
    ): SignUpResponse

    @POST
    @FormUrlEncoded
    suspend fun rate(
        @Header("Authorization") authorization: String,
        @Url url: String,
        @Field("rating") rating: Float
    ): SignUpResponse

    @Multipart
    @POST("farmer/picture")
    suspend fun picture(
        @Header("Authorization") authorization: String,
        @Part picture: MultipartBody.Part
    ): SignUpResponse
}