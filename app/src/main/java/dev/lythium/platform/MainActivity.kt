package dev.lythium.platform

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.lythium.platform.ui.SavedJourneysScreen
import dev.lythium.platform.ui.SharedViewModel
import dev.lythium.platform.ui.pages.SearchScreen
import dev.lythium.platform.ui.pages.TrainsListScreen
import dev.lythium.platform.ui.theme.NavigationItem
import dev.lythium.platform.ui.theme.PlatformTheme

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    private val stations = Backend.getStations()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Content(stations)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(stations: List<Station>) {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()


    PlatformTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) },
                content = { padding ->
                    Box(
                        modifier = Modifier
                            .padding(padding)
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        Navigation(navController = navController, stations = stations)
                    }
                },
                topBar = {
                    !sharedViewModel.topBarShow && return@Scaffold
                    TopAppBar(
                        modifier = Modifier
                            .statusBarsPadding()
                            .fillMaxWidth(),
                        title = { Text(sharedViewModel.topBarTitle) },
                        navigationIcon = {
                            !sharedViewModel.topBarShowBack && return@TopAppBar

                            IconButton(
                                onClick = { navController.navigateUp() }
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        },
                        actions = {
                            sharedViewModel.topBarActions ?: return@TopAppBar
                            
                            sharedViewModel.topBarActions?.invoke(this)
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
                        )
                    )
                }
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

    val currentRoute =
        remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
    ) {
        items.forEach { item ->

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute.value == item.route, // TODO: Need to fix this on app open
                onClick = {
                    if (currentRoute.value == item.route) return@NavigationBarItem
                    currentRoute.value = item.route
                    navController.navigate(item.route)
                }
            )
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    stations: List<Station>
) {
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController,
        startDestination = NavigationItem.SavedJourneys.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(NavigationItem.SavedJourneys.route) {
            SavedJourneysScreen()
        }
        composable(NavigationItem.Search.route) {
            SearchScreen(stations, navController, sharedViewModel)
        }
        composable(NavigationItem.TrainsList.route) {
            TrainsListScreen(navController, sharedViewModel)
        }
    }
}