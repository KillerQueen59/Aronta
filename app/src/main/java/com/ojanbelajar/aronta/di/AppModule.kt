package com.ojanbelajar.aronta.di

import android.app.Application
import com.ojanbelajar.aronta.data.source.ArontaRepository
import com.ojanbelajar.aronta.data.source.Repository
import com.ojanbelajar.aronta.data.source.local.LocalDataInterface
import com.ojanbelajar.aronta.data.source.local.LocalDataSource
import com.ojanbelajar.aronta.data.source.local.room.ArontaDao
import com.ojanbelajar.aronta.data.source.local.room.ArontaDatabase
import com.ojanbelajar.aronta.data.source.remote.RemoteDataInterface
import com.ojanbelajar.aronta.data.source.remote.RemoteDataSource
import com.ojanbelajar.aronta.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideBase() = "https://apifarmer.herokuapp.com/api/v1/"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
    }.build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient,BASEURL: String): Retrofit = Retrofit.Builder().apply {
        baseUrl(BASEURL)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideContentRemoteSource(remoteSource: RemoteDataSource): RemoteDataInterface = remoteSource

    @Provides
    @Singleton
    fun provideContentLocalSource(localSource: LocalDataSource): LocalDataInterface =  localSource

    @Provides
    @Singleton
    fun provideContentRepository(
        contentRemoteSource: RemoteDataInterface,
        contentLocalSource: LocalDataInterface,
    ): Repository = ArontaRepository(contentRemoteSource,contentLocalSource)

    @Singleton
    @Provides
    fun provideContentDatabase(
        application: Application
    ): ArontaDatabase = ArontaDatabase.getInstance(application)

    @Singleton
    @Provides
    fun contentDao(db: ArontaDatabase): ArontaDao = db.arontaDao()
}