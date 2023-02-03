package com.brandyodhiambo.datastoresandroid.data.local

import androidx.datastore.core.DataStore
import com.brandyodhiambo.datastoresandroid.ThemePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class ThemeRepository(
    private val themePreferencesDataStore: DataStore<ThemePreferences>
) {
    val themeFlow: Flow<ThemePreferences> = themePreferencesDataStore.data.catch { exception ->
        if (exception is Exception) {
            emit(ThemePreferences.getDefaultInstance())
        } else {
            throw exception
        }
    }

    suspend fun saveThemePreference(theme: Int) {
        themePreferencesDataStore.updateData { themePreference ->
            themePreference.toBuilder().setTheme(theme.toString()).build()
        }
    }
}
