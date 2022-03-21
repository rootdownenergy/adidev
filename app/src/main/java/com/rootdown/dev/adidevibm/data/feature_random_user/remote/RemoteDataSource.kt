package com.rootdown.dev.adidevibm.data.feature_random_user.remote

import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserX
import com.rootdown.dev.adidevibm.data.feature_random_user.net.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val apiService: ApiService) {
    fun LoadUser() = UserX.createRandom()
    fun  loadUser() = apiService.loadUser()
    //fun loadUsers() = (0..10).map { User.createRandom()
    fun  loadUsers(result: Int) = apiService.loadUsers(result)
}