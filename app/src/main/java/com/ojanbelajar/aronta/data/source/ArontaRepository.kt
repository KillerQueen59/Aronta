package com.ojanbelajar.aronta.data.source

import android.util.Log
import com.ojanbelajar.aronta.data.source.local.LocalDataInterface
import com.ojanbelajar.aronta.data.source.local.entity.BuruhEntity
import com.ojanbelajar.aronta.data.source.local.entity.LearnEntity
import com.ojanbelajar.aronta.data.source.local.entity.NewsEntity
import com.ojanbelajar.aronta.data.source.local.entity.OrderEntity
import com.ojanbelajar.aronta.data.source.remote.RemoteDataInterface
import com.ojanbelajar.aronta.data.source.remote.body.OrderBody
import com.ojanbelajar.aronta.data.source.remote.body.SignUpBody
import com.ojanbelajar.aronta.data.source.remote.network.ApiResponse
import com.ojanbelajar.aronta.data.source.remote.response.*
import com.ojanbelajar.aronta.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ArontaRepository @Inject constructor(
    private val contentRemoteSource: RemoteDataInterface,
    private val contentLocalSource: LocalDataInterface
): Repository{
    override fun login(body: RequestBody): Flow<Resource<LoginResponse>> =
        object : NetworkOnlyResource<LoginResponse,LoginResponse>(){
            override fun loadFromNetwork(data: LoginResponse): Flow<LoginResponse> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<LoginResponse>> {
                return contentRemoteSource.login(body)
            }

        }.asFlow()

    override fun signUp(body: SignUpBody): Flow<Resource<SignUpResponse>> =
        object : NetworkOnlyResource<SignUpResponse,SignUpResponse>(){
            override fun loadFromNetwork(data: SignUpResponse): Flow<SignUpResponse> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<SignUpResponse>> {
                return contentRemoteSource.signUp(body)
            }

        }.asFlow()

    override fun order(body: OrderBody, token: String): Flow<Resource<SignUpResponse>> =
        object : NetworkOnlyResource<SignUpResponse,SignUpResponse>(){
            override fun loadFromNetwork(data: SignUpResponse): Flow<SignUpResponse> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<SignUpResponse>> {
                return contentRemoteSource.order(body,token)
            }

        }.asFlow()

    override fun getWorkerSearch(search: String, token: String): Flow<Resource<List<BuruhEntity>>> =
       object : NetworkOnlyResource<List<BuruhEntity>, WorkerFullResponse>() {
           override fun loadFromNetwork(data: WorkerFullResponse): Flow<List<BuruhEntity>> {
               return flowOf(DataMapper.mapWorkerResponseToBuruhEntity(data))
           }

           override suspend fun createCall(): Flow<ApiResponse<WorkerFullResponse>> {
                return contentRemoteSource.getWorkerSearch(search,token)
           }

       }.asFlow()

    override fun getWorker(token: String): Flow<Resource<List<BuruhEntity>>>  =
        object : NetworkOnlyResource<List<BuruhEntity>, WorkerFullResponse>() {
            override fun loadFromNetwork(data: WorkerFullResponse): Flow<List<BuruhEntity>> {
                return flowOf(DataMapper.mapWorkerResponseToBuruhEntity(data))
            }

            override suspend fun createCall(): Flow<ApiResponse<WorkerFullResponse>> {
                return contentRemoteSource.getWorker(token)
            }

        }.asFlow()


    override fun getNews(token: String): Flow<Resource<List<NewsEntity>>> =
        object : NetworkOnlyResource<List<NewsEntity>, NewsResponse>() {
            override fun loadFromNetwork(data: NewsResponse): Flow<List<NewsEntity>> {
                return flowOf(DataMapper.mapNewsResponseToNewsEntity(data))
            }

            override suspend fun createCall(): Flow<ApiResponse<NewsResponse>> {
                return contentRemoteSource.getNews(token)
            }

        }.asFlow()

    override fun getLesson(token: String): Flow<Resource<List<LearnEntity>>> =
         object : NetworkOnlyResource<List<LearnEntity>, LessonResponse>() {
            override fun loadFromNetwork(data: LessonResponse): Flow<List<LearnEntity>> {
                return flowOf(DataMapper.mapLessonResponseToLearnEntity(data))
            }

            override suspend fun createCall(): Flow<ApiResponse<LessonResponse>> {
                return contentRemoteSource.getLesson(token)
            }
        }.asFlow()

    override fun getOrder(token: String,id: String): Flow<Resource<List<OrderEntity>>>  =
        object : NetworkOnlyResource<List<OrderEntity>, OrderFullResponse>() {
            override fun loadFromNetwork(data: OrderFullResponse): Flow<List<OrderEntity>> {
                return flowOf(DataMapper.mapOrderFullResponseToOrderResponse(data))
            }

            override suspend fun createCall(): Flow<ApiResponse<OrderFullResponse>> {
                return contentRemoteSource.getOrder(token,id)
            }
        }.asFlow()

    override fun cancel(token: String, id: String): Flow<Resource<SignUpResponse>> =
        object : NetworkOnlyResource<SignUpResponse,SignUpResponse>(){
            override fun loadFromNetwork(data: SignUpResponse): Flow<SignUpResponse> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<SignUpResponse>> {
                return contentRemoteSource.cancel(token,id)
            }

        }.asFlow()

    override fun rate(token: String, id: String, rate: Float): Flow<Resource<SignUpResponse>> =
        object : NetworkOnlyResource<SignUpResponse,SignUpResponse>(){
            override fun loadFromNetwork(data: SignUpResponse): Flow<SignUpResponse> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<SignUpResponse>> {
                return contentRemoteSource.rate(token,id,rate)
            }

        }.asFlow()

    override fun picture(
        token: String,
        picture: MultipartBody.Part
    ): Flow<Resource<SignUpResponse>> =
        object : NetworkOnlyResource<SignUpResponse,SignUpResponse>(){
            override fun loadFromNetwork(data: SignUpResponse): Flow<SignUpResponse> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<SignUpResponse>> {
                return contentRemoteSource.picture(token,picture)
            }

        }.asFlow()
}