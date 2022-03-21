package com.rootdown.dev.adidevibm.data.feature_random_user.net

import com.rootdown.dev.adidevibm.data.feature_random_user.models.remote.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api")
    fun loadUser(): Call<UsersResponse>

    @GET("api")
    fun loadUsers(@Query("results") result: Int): Call<UsersResponse>
}