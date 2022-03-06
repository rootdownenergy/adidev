package com.rootdown.dev.adidevibm.viewmodel.feature_random_user


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rootdown.dev.adidevibm.model.feature_random_user.net.UserServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val userService: UserServiceImpl,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    // get random user
    fun connect(){
        viewModelScope.launch {
            userService.getuser()
        }
    }
}