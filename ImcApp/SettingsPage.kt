package com.example.imcapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

@Composable
fun SettingsPage(isDarkTheme: Boolean, onThemeToggle: () -> Unit) {
    Column(modifier = Modifier
            .fillMaxSize()
            .padding(all = 32.dp)) {
        Text(text = "Definições", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.align(alignment = Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(64.dp))

        Text(text = "Tema Escuro:", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(bottom = 16.dp))
        Switch(
            checked = isDarkTheme,
            onCheckedChange = { onThemeToggle() }
        )
    }
}
