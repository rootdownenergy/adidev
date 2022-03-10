package com.rootdown.dev.adidevibm.ui.viewmodel.feature_random_user


import android.util.Log
import androidx.lifecycle.*
import com.rootdown.dev.adidevibm.data.feature_random_user.repo.UserRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repo: UserRepoImpl) : ViewModel() {

    // flow of users for recyclerview
    val users = repo.getUsers
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