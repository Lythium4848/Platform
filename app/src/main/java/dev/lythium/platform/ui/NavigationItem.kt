package dev.lythium.platform.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object SavedJourneys : NavigationItem("saved_journeys", Icons.Rounded.Star, "Saved Journeys")
    object Search : NavigationItem("search", Icons.Rounded.Search, "Search")
    object TrainsList : NavigationItem("trains_list", Icons.Rounded.Search, "Trains List")
}