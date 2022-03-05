package com.rootdown.dev.adidevibm.data.feature_random_user.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {

}