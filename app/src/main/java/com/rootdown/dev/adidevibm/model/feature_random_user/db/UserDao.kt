package com.rootdown.dev.adidevibm.model.feature_random_user.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<Users>)
    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<Users>>
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): LiveData<Users>
}