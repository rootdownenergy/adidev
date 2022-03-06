package com.rootdown.dev.adidevibm.di

import android.app.Application
import com.rootdown.dev.adidevibm.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApp(): Application {
        return App.instance
    }
}