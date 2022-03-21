package com.rootdown.dev.adidevibm.data.feature_random_user.local

import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserLocal
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    suspend fun saveUser(user: UserLocal)
    suspend fun getUser() : Flow<UserLocal>
}