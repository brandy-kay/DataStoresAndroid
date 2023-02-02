package com.brandyodhiambo.datastoresandroid.utils

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.brandyodhiambo.datastoresandroid.utils.Constants.PREFERENCE_NAME

val Context.dataStore by preferencesDataStore(
    name = PREFERENCE_NAME
)