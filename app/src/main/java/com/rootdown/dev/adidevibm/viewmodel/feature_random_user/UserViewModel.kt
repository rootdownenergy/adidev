package com.rootdown.dev.adidevibm.viewmodel.feature_random_user


import android.util.Log
import androidx.lifecycle.*
import com.rootdown.dev.adidevibm.model.feature_random_user.db.Users
import com.rootdown.dev.adidevibm.model.feature_random_user.repo.UserRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException


class UserViewModel(private val repo: UserRepoImpl) : ViewModel() {

    // flow of users for recyclerview
    val users: LiveData<List<Users>> = repo.getUsers()
    // get random user
    fun connect(){
        viewModelScope.launch {
            try {
                repo.userDB()
            } catch (netE: IOException) {
                Log.w("ERRORXXX", netE.message.toString())
            }
        }
    }
}