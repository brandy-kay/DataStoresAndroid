package com.brandyodhiambo.datastoresandroid.data.local

import androidx.datastore.core.Serializer
import com.brandyodhiambo.datastoresandroid.ThemePreferences
import java.io.InputStream
import java.io.OutputStream

object ThemePreferenceSerializer : Serializer<ThemePreferences> {
    override val defaultValue: ThemePreferences = ThemePreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ThemePreferences {
        return ThemePreferences.parseFrom(input)
    }

    override suspend fun writeTo(t: ThemePreferences, output: OutputStream) {
        t.writeTo(output)
    }
}
