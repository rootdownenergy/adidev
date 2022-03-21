package com.rootdown.dev.adidevibm.data.feature_random_user.repo

import com.rootdown.dev.adidevibm.data.feature_random_user.local.LocalDataSource
import com.rootdown.dev.adidevibm.data.feature_random_user.local.LocalSourceImpl
import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserLocal
import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserX
import com.rootdown.dev.adidevibm.data.feature_random_user.remote.RemoteDataSource
import com.rootdown.dev.adidevibm.data.feature_random_user.repo.util.User
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

class UserRepo @Inject constructor(
    val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    private val savedUser = AtomicReference(UserX())
    suspend fun getSavedUser() = localDataSource.loadUser()
    fun getRandomUser() = run { remoteDataSource.loadUser() }
    fun getRemoteUsers(result : Int ) = remoteDataSource.loadUsers(result)

    suspend fun getUser(forceUpdate: Boolean): UserX {
        if (forceUpdate) {
            val user = remoteDataSource.LoadUser()

            localDataSource.saveUser(user)
            savedUser.set(user)
        }
        return savedUser.get()
    }
}