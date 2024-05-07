package dev.lythium.platform.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.lythium.platform.ui.SharedViewModel
import dev.lythium.platform.ui.components.TrainDeparture

@Composable
fun TrainsListScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val data = sharedViewModel.stationBoardData ?: return

    val fromName = data.locationName
    val fromCRS = data.crs
    val toName = data.filterLocationName
    val toCRS = data.filtercrs
    val trainsList = data.trainServices

    sharedViewModel.topBarShow = true
    sharedViewModel.topBarTitle = "$fromName to $toName"
    sharedViewModel.topBarShowBack = true
    sharedViewModel.topBarActions = {
        IconButton(onClick = { println("hi") }) {
            Icon(Icons.Outlined.Star, contentDescription = "Bookmark")
        }
    }


    Scaffold { padding ->
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                trainsList ?: Text(
                    text = "No trains available",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(24F, TextUnitType.Sp)
                )

                trainsList?.forEach { train ->
                    TrainDeparture(train)
                }
            }
        }
    }
}