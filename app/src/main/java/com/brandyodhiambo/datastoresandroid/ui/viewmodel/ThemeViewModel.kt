package com.brandyodhiambo.datastoresandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.datastoresandroid.ThemePreferences
import com.brandyodhiambo.datastoresandroid.data.local.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val themeRepository: ThemeRepository
) : ViewModel() {

    val themeFlow: Flow<ThemePreferences> get() = themeRepository.themeFlow

    fun setTheme(theme: Int) {
        viewModelScope.launch {
            themeRepository.saveThemePreference(theme)
        }
    }
}
