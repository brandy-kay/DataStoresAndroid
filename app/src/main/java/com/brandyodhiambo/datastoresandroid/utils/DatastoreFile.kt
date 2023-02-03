package com.brandyodhiambo.datastoresandroid.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.brandyodhiambo.datastoresandroid.ThemePreferences
import com.brandyodhiambo.datastoresandroid.data.local.ThemePreferenceSerializer
import com.brandyodhiambo.datastoresandroid.utils.Constants.PREFERENCE_NAME

val Context.themePreferencesStore: DataStore<ThemePreferences> by dataStore(
    fileName = PREFERENCE_NAME,
    serializer = ThemePreferenceSerializer
)
