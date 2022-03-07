package com.rootdown.dev.adidevibm.viewmodel.feature_random_user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.rootdown.dev.adidevibm.model.feature_random_user.db.AppDatabase
import com.rootdown.dev.adidevibm.model.feature_random_user.db.Users
import com.rootdown.dev.adidevibm.model.feature_random_user.repo.UserDetailImpl
import com.rootdown.dev.adidevibm.model.feature_random_user.repo.UserRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(application: Application, state: SavedStateHandle) : AndroidViewModel(application) {
    private val repo = UserDetailImpl(AppDatabase.getInstance(application))
    private val userId: Int = state.get<Int>("userId") ?: 0
    val u: LiveData<Users> = repo.getUserById(userId)
}