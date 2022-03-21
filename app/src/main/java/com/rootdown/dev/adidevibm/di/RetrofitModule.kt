package com.rootdown.dev.adidevibm.di

import com.rootdown.dev.adidevibm.data.feature_random_user.net.ApiService
import com.rootdown.dev.adidevibm.data.feature_random_user.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val baseURL = "https://randomuser.me/"

    @Provides
    @Singleton
    fun getRemoteRepository(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }


    @Provides
    @Singleton
    fun getRetroServiceInterface(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun getRetroFitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}