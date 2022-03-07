package com.rootdown.dev.adidevibm.model.feature_random_user.repo

import androidx.lifecycle.LiveData
import com.rootdown.dev.adidevibm.model.feature_random_user.db.AppDatabase
import com.rootdown.dev.adidevibm.model.feature_random_user.db.Users

class UserDetailImpl(private val db: AppDatabase) {
    fun getUserById(id: Int): LiveData<Users> {
        return db.userDao().getUserById(id)
    }
}