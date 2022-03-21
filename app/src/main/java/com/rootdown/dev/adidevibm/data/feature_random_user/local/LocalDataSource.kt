package com.rootdown.dev.adidevibm.data.feature_random_user.local


import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserLocal
import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserX
import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserX.Companion.createRandom
import com.rootdown.dev.adidevibm.data.feature_random_user.repo.util.User
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

class LocalDataSource @Inject constructor(  // TODO (3 points): - DONE Inject all dependencies instead of instantiating some
    private val preferencesManager: LocalSourceImpl,
    private val moshi : Moshi
) {

    // private val moshi = Moshi.Builder().build()
    suspend fun loadUser(): UserX? {
        val serializedUser = preferencesManager.getUser().toString()
        val jsonAdapter = moshi.adapter(
            UserX::class.java
        )
        return try {
            // TODO - DONE - (1 point): Address Android Studio warning
            //Type mismatch require "String" provide "String?"
            val user = jsonAdapter.fromJson(serializedUser)
            user ?: createRandom()
        } catch (e: Exception) {
            e.printStackTrace()
            createRandom()
        }
    }
    suspend fun saveUser(user: UserX) {
        val jsonAdapter = moshi.adapter(
            UserX::class.java
        )
        val serializedUser = jsonAdapter.toJson(user)
        val x = UserLocal(user=serializedUser)
        preferencesManager.saveUser(x)
    }
    //  fun loadUser() = User.createRandom()
    fun loadUsers() = (0..10).map { UserX.createRandom() }
}