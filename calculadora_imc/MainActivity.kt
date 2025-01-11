package com.a039679.calculadora_imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.a039679.calculadora_imc.ui.theme.Calculadora_IMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculadora_IMCTheme {
                MyApp()
            }
        }
    }
}


@Composable
fun MyApp() {
    val navController = rememberNavController() // NavHostController
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavigationHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.Page1,
        NavigationItem.Page2,
        NavigationItem.Page3,
    )
    NavigationBar {
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    // Se for a tela Page1, voltar para a tela inicial
                    if (item.route == NavigationItem.Page1.route) {
                        navController.popBackStack(NavigationItem.Page1.route, false)
                    } else {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = NavigationItem.Page1.route, modifier = modifier) {
        composable(NavigationItem.Page1.route) {
            IMC(navController)
        }
        composable(NavigationItem.Page2.route) {
            Page2()
        }
        composable(NavigationItem.Page3.route) {
            Page3()
        }
    }
}
// Helper para encontrar a rota atual
@Composable
fun currentRoute(navController: NavHostController): String? { // Troque para NavHostController
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}


@Composable
fun Page2() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("Histórico", style = MaterialTheme.typography.headlineMedium)
    }
}


@Composable
fun Page3() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("Definições", style = MaterialTheme.typography.headlineMedium)
    }
}

// Representação de itens do menu
sealed class NavigationItem(val route: String, val icon: ImageVector, val title: String) {
    object Page1 : NavigationItem("page1", Icons.Default.Home, "Início")
    object Page2 : NavigationItem("page2", Icons.Default.Info, "Histórico")
    object Page3 : NavigationItem("page3", Icons.Default.Settings, "Definições")
}