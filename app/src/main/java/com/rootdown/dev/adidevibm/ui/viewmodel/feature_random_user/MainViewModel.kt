package com.rootdown.dev.adidevibm.ui.viewmodel.feature_random_user

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.rootdown.dev.adidevibm.data.feature_random_user.models.remote.UsersResponse
import com.rootdown.dev.adidevibm.data.feature_random_user.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application, state: SavedStateHandle) : AndroidViewModel(application) {

    private val executor = Executors.newSingleThreadExecutor()
    val scope = CoroutineScope(SupervisorJob() + executor.asCoroutineDispatcher())

    @Inject
    lateinit var userRepository: UserRepo
    private var userLiveData: MutableLiveData<UsersResponse?> = MutableLiveData()
    private var usersLiveData: MutableLiveData<UsersResponse?> = MutableLiveData()


    fun getUserLiveDataObserver(): MutableLiveData<UsersResponse?> {
        return userLiveData
    }

    fun getUsersLiveDataObserver(): MutableLiveData<UsersResponse?> {
        return usersLiveData
    }

    fun getUser() = viewModelScope.launch {
        val call: Call<UsersResponse> = userRepository.getRandomUser()
        call.enqueue(object : Callback<UsersResponse> {
            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                val a = t.localizedMessage
                Log.d("TAG", "onFailure: $a ")
                userLiveData.postValue(null)
            }
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if(response.isSuccessful) {
                    // It will persist the user that was last shown
                        scope.launch {
                            userRepository.localDataSource.saveUser(response.body()!!.results[0])
                        }

                    userLiveData.postValue(response.body())
                } else {
                    userLiveData.postValue(null)
                    Log.d("TAG", "onFailure: $response ")
                }
            }
        })
    }


    fun getUsers(result : Int) = viewModelScope.launch {
        val call: Call<UsersResponse> = userRepository.getRemoteUsers(result)
        call.enqueue(object : Callback<UsersResponse> {
            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                val a = t.localizedMessage
                Log.d("TAG", "onFailure: $a ")
                userLiveData.postValue(null)

            }
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if(response.isSuccessful) {
                    usersLiveData.postValue(response.body())
                } else {
                    Log.d("TAG", "onFailure: $response ")
                    userLiveData.postValue(null)
                }
            }
        })
    }







}