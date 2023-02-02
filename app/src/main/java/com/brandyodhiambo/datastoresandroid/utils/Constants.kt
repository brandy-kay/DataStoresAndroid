package com.brandyodhiambo.datastoresandroid.utils

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object Constants {

    const val PREFERENCE_NAME = "com.brandyodhiambo.datastoresandroid.preferences"
    val THEME_VALUE = intPreferencesKey(name = "theme_value")
}

