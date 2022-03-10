package com.rootdown.dev.adidevibm.data.feature_random_user.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rootdown.dev.adidevibm.data.feature_random_user.db.util.ListStringConverter


@Database(
    entities = [Users::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "app.db")
                .build()
    }
}