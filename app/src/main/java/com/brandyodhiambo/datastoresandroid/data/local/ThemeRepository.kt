package com.brandyodhiambo.datastoresandroid.data.local

import androidx.datastore.core.DataStore
import com.brandyodhiambo.datastoresandroid.ThemePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ThemeRepository(
    private val themePreferencesDataStore: DataStore<ThemePreferences>
) {
    val themeFlow: Flow<Int> = themePreferencesDataStore.data
        .catch { exception ->
            if (exception is Exception) {
                emit(ThemePreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }
        .map { themePreferences ->
            themePreferences.theme
        }

    suspend fun saveThemePreference(theme: Int) {
        themePreferencesDataStore.updateData { themePreference ->
            themePreference.toBuilder().setTheme(theme).build()
        }
    }
}
