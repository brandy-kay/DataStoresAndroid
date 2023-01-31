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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandyodhiambo.datastoresandroid.ui.theme.DataStoresAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoresAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppThemeComponent()
                }
            }
        }
    }
}

@Composable
fun AppThemeComponent() {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.choose_color),
            fontSize = MaterialTheme.typography.h5.fontSize,
            fontWeight = MaterialTheme.typography.h5.fontWeight,
            fontFamily = MaterialTheme.typography.h5.fontFamily,
            color = MaterialTheme.colors.onBackground
        )

        val radioOptions = listOf<String>("Device settings", "Light Mode", "Dark Mode")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            radioOptions.forEach { text ->
                Row(
                    modifier = Modifier.fillMaxWidth().selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
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
                            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                        }
                    )

                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 16.dp)
                    )
//
                }
            }
        }

        Text(
            text = stringResource(R.string.device_settings),
            fontSize = MaterialTheme.typography.h5.fontSize,
            fontWeight = MaterialTheme.typography.h5.fontWeight,
            fontFamily = MaterialTheme.typography.h5.fontFamily,
            color = MaterialTheme.colors.onBackground
        )
    }
}
