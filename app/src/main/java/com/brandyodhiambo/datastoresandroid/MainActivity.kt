package com.brandyodhiambo.datastoresandroid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.datastoresandroid.data.local.ThemePreference
import com.brandyodhiambo.datastoresandroid.ui.theme.DataStoresAndroidTheme
import com.brandyodhiambo.datastoresandroid.ui.theme.Theme
import com.brandyodhiambo.datastoresandroid.ui.viewmodel.ThemeViewModel
import com.brandyodhiambo.datastoresandroid.utils.dataStore
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val themePreference = ThemePreference(dataStore = dataStore)
        val viewModel = ThemeViewModel(themePreference = themePreference)

        setContent {
            val theme = viewModel.themeFlow.collectAsState(
                initial = Theme.FOLLOW_SYSTEM.themeValue,
                context = Dispatchers.Main.immediate
            ).value
            DataStoresAndroidTheme(
                theme = theme
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppThemeComponent(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun AppThemeComponent(viewModel: ThemeViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.choose_color),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.onBackground
        )

        val radioOptions = listOf<String>("Device settings", "Light Mode", "Dark Mode")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            radioOptions.forEach { text ->
                Row(
                    modifier = Modifier.fillMaxWidth().selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            when (text) {
                                "Light Mode" -> {
                                    viewModel.setTheme(Theme.LIGHT_THEME.themeValue)
                                }
                                "Dark Mode" -> {
                                    viewModel.setTheme(Theme.DARK_THEME.themeValue)
                                }
                                else -> {
                                    viewModel.setTheme(Theme.FOLLOW_SYSTEM.themeValue)
                                }
                            }
                        }
                    )
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val context = LocalContext.current

                    RadioButton(
                        selected = (text == selectedOption),
                        modifier = Modifier.padding(all = Dp(value = 8F)),
                        onClick = {
                            onOptionSelected(text)
                            when (text) {
                                "Light Mode" -> {
                                    viewModel.setTheme(Theme.LIGHT_THEME.themeValue)
                                }
                                "Dark Mode" -> {
                                    viewModel.setTheme(Theme.DARK_THEME.themeValue)
                                }
                                else -> {
                                    viewModel.setTheme(Theme.FOLLOW_SYSTEM.themeValue)
                                }
                            }
                            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                        }
                    )

                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        Text(
            text = stringResource(R.string.device_settings),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
