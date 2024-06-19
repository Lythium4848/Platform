package dev.lythium.platform.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.lythium.platform.Definitions.CallingPoint
import dev.lythium.platform.Definitions.ServiceItemWithCallingPoints

@Composable
fun TrainDeparture(train: ServiceItemWithCallingPoints) {
    var timeArrival = "Unknown"

    val origin = train.origin[0]
    val destination = train.destination[0]

    train.subsequentCallingPoints?.get(0)?.callingPoint?.forEach { callingPoint: CallingPoint ->
        if (callingPoint.crs == destination.crs) {
            timeArrival = callingPoint.st
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
        modifier = Modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.small)
            .fillMaxWidth()
            .height(80.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            item {
                Text(
                    text = "${train.std ?: "Unknown"} - ${timeArrival}",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}