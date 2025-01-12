package com.example.imcapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imcapp.HomePage
import com.example.imcapp.HistoryPage
import com.example.imcapp.SettingsPage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings

@Composable
fun MyApp(isDarkTheme: Boolean, onThemeToggle: () -> Unit) {
    val navController = rememberNavController()
    val historyViewModel: HistoryViewModel = viewModel()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("home") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Início") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("history") },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Histórico") },
                    label = { Text("Histórico") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("settings") },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Definições") },
                    label = { Text("Definições") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomePage(navController, historyViewModel)
            }
            composable("history") {
                HistoryPage(historyViewModel)
            }
            composable("settings") {
                SettingsPage(isDarkTheme, onThemeToggle)
            }
        }
    }
}
