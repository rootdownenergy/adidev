package com.rootdown.dev.adidevibm.viewmodel.feature_random_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rootdown.dev.adidevibm.model.feature_random_user.repo.UserRepoImpl

class ViewModelFactory(private val repo: UserRepoImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}