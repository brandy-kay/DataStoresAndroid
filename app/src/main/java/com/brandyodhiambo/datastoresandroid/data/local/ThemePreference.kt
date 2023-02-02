package com.brandyodhiambo.datastoresandroid.data.local

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.brandyodhiambo.datastoresandroid.utils.Constants.THEME_VALUE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemePreference(
    private val dataStore: DataStore<Preferences>
) {

    val themeFlow: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[THEME_VALUE] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }

    suspend fun saveTheme(theme: Int) {
        dataStore.edit { preferences ->
            preferences[THEME_VALUE] = theme
        }
    }
}
