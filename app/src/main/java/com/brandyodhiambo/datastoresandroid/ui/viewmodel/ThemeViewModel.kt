package com.brandyodhiambo.datastoresandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.datastoresandroid.data.local.ThemePreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val themePreference: ThemePreference
) : ViewModel() {

    val themeFlow: Flow<Int> get() = themePreference.themeFlow

    fun setTheme(theme: Int) {
        viewModelScope.launch {
            themePreference.saveTheme(theme)
        }
    }
}
