package com.rootdown.dev.adidevibm.data.feature_random_user.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    @Query("SELECT * FROM users WHERE name LIKE :query")
    fun userByName(query: String)
}