package com.rootdown.dev.adidevibm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rootdown.dev.adidevibm.R
import com.rootdown.dev.adidevibm.view.feature_random_user.UserAdapter

class MainActivity : AppCompatActivity() {
    lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}