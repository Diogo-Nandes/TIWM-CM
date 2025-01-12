package com.example.imcapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

@Composable
fun SettingsPage(isDarkTheme: Boolean, onThemeToggle: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Definições")
        Switch(
            checked = isDarkTheme,
            onCheckedChange = { onThemeToggle() }
        )
    }
}
