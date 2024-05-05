package dev.lythium.platform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.lythium.platform.ui.SavedJourneysScreen
import dev.lythium.platform.ui.SearchScreen
import dev.lythium.platform.ui.theme.NavigationItem
import dev.lythium.platform.ui.theme.PlatformTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Content()
        }
    }
}

@Composable
fun Content() {
    val navController = rememberNavController()

    PlatformTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) },
                content = { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        Navigation(navController = navController)
                    }
                },
            )
        }

    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.SavedJourneys,
        NavigationItem.Search
    )

    val currentRoute = remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }


    NavigationBar {
        items.forEach { item ->

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute.value == item.route,
                onClick = {
                    currentRoute.value = item.route
                    navController.navigate(item.route)
                }
            )
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.SavedJourneys.route) {
        composable(NavigationItem.SavedJourneys.route) {
            SavedJourneysScreen()
        }
        composable(NavigationItem.Search.route) {
            SearchScreen()
        }
    }
}