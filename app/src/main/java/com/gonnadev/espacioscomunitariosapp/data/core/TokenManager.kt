package com.gonnadev.espacioscomunitariosapp.data.core

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenManager @Inject constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveToken(token: String) {
        dataStore.edit{ preferences ->
            preferences[stringPreferencesKey("token")] = token
        }
    }

    suspend fun getToken() = dataStore.data.first()[stringPreferencesKey("token")]
}