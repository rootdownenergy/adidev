package com.rootdown.dev.adidevibm.ui.feature_random_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.rootdown.dev.adidevibm.R
import com.rootdown.dev.adidevibm.adapter.UserAdapter
import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserX
import com.rootdown.dev.adidevibm.data.feature_random_user.repo.util.User
import com.rootdown.dev.adidevibm.databinding.ActivityAlternateBinding
import com.rootdown.dev.adidevibm.databinding.ActivityMainBinding
import com.rootdown.dev.adidevibm.ui.viewmodel.feature_random_user.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AlternateActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlternateBinding
    val loadFromLocal = false
    //@Inject lateinit var userRepository: UserRepository
    private lateinit var mainViewModel: MainViewModel
    private var randomUser: UserX = UserX()
        set(value) {
            // TODO - DONE (1 point): Use Glide to load images
            Glide.with(this).load(value.picture!!.medium)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_autorenew_24).into(binding.mainImage);
            binding.mainName.text = value.name!!.first
            binding.mainEmail.text = value.email
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlternateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        // vm = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

        if(loadFromLocal){
            lifecycleScope.launch {
                randomUser = mainViewModel.userRepository.getSavedUser()!!
            }
        }else{
            mainViewModel.getUser()
        }

        binding.mainSeeDetailsButton.setOnClickListener { navigateDetails(randomUser) }
        binding.mainRefreshButton.setOnClickListener {
            if(loadFromLocal){
                lifecycleScope.launch {
                    randomUser = mainViewModel.userRepository.getUser(true)
                }
            }else{
                mainViewModel.getUser()
            }


        }
        binding.mainUserListButton.setOnClickListener {
            if(loadFromLocal){
                lifecycleScope.launch {
                    val users = mainViewModel.userRepository.getUser(true)
                }
                // setAdapter(users)
            }else{
                mainViewModel.getUsers(10)
            }
        }

    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getUsersLiveDataObserver().observe(this
        ) { t ->
            if (t != null) {
                setAdapter(t.results)
            } else {
                mainViewModel.getUser()
            }
        }


        mainViewModel.getUserLiveDataObserver().observe(this
        ) { t ->
            if (t != null) {
                randomUser = t.results[0]
            } else {
                mainViewModel.getUsers(10)
            }
        }

    }



    private fun setAdapter(it: List<UserX>) {
        val adapter = UserAdapter(it) { user->
            navigateDetails(user)
        }
        binding.mainUserList.adapter = adapter
    }

    private fun navigateDetails(user: UserX) {
        val putExtra = Intent(this, DetailsActivity::class.java).putExtra("saved-user-key", user)
        startActivity(putExtra)
    }
}