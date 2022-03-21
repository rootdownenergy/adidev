package com.rootdown.dev.adidevibm.data.feature_random_user.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val DATASTORE = "USER"

val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = DATASTORE)

class LocalSourceImpl(private val context: Context) : LocalSource {

    override suspend fun saveUser(user: UserLocal) {
        context.datastore.edit { users ->
            users[NAME] = user.user
        }
    }

    override suspend fun getUser() = context.datastore.data.map { userIn ->
        UserLocal(
            user = userIn[NAME]!!
        )
    }

    companion object{
        val NAME = stringPreferencesKey("NAME")
    }
}