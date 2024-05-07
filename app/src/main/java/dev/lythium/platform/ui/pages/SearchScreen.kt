package dev.lythium.platform.ui.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.lythium.platform.Backend
import dev.lythium.platform.Station
import dev.lythium.platform.ui.AutoCompleteTextField
import dev.lythium.platform.ui.SharedViewModel
import dev.lythium.platform.ui.theme.NavigationItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    stations: List<Station>,
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        var departFrom by remember { mutableStateOf<Station?>(null) }
        val departFromColors = OutlinedTextFieldDefaults.colors()

        var arriveAt by remember { mutableStateOf<Station?>(null) }
        val arriveAtColors = OutlinedTextFieldDefaults.colors()

        AutoCompleteTextField(
            items = stations,
            label = "Depart From",
            modifier = Modifier
                .fillMaxWidth(),
            onSelect = {
                departFrom = it
            },
            colors = departFromColors
        )

        AutoCompleteTextField(
            items = stations,
            label = "Arrive At",
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 4.dp),
            onSelect = {
                arriveAt = it
            },
            colors = arriveAtColors
        )

        Button(
            onClick = {
                arriveAt?.crs ?: return@Button // TODO: Add proper handling for missing values
                departFrom?.crs ?: return@Button

                val data = Backend.getTrainsTo(departFrom!!.crs, arriveAt!!.crs)
                sharedViewModel.stationBoardData = data

                navController.navigate(NavigationItem.TrainsList.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 4.dp)
        ) {
            Text(
                text = "Search",
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .basicMarquee()
                    .fillMaxWidth()

            )
        }

    }
}
