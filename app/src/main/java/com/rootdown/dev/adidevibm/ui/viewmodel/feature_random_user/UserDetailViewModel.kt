package com.rootdown.dev.adidevibm.ui.viewmodel.feature_random_user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.rootdown.dev.adidevibm.data.feature_random_user.db.AppDatabase
import com.rootdown.dev.adidevibm.data.feature_random_user.db.Users
import com.rootdown.dev.adidevibm.data.feature_random_user.repo.UserDetailImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(application: Application, state: SavedStateHandle) : AndroidViewModel(application) {
    private val repo = UserDetailImpl(AppDatabase.getInstance(application))
    private val userId: Int = state.get<Int>("userId") ?: 0
    val u: LiveData<Users> = repo.getUserById(userId)
}