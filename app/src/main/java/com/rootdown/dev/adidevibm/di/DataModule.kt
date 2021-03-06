package com.rootdown.dev.adidevibm.di

import android.content.Context
import androidx.room.Room
import com.rootdown.dev.adidevibm.data.feature_random_user.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ServiceComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app.db")
}