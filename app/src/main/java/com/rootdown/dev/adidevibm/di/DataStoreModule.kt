package com.rootdown.dev.adidevibm.di

import android.content.Context
import com.rootdown.dev.adidevibm.data.feature_random_user.local.LocalSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideLocalSource(@ApplicationContext context: Context) = LocalSourceImpl(context)


}