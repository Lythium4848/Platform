package dev.lythium.platform.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.lythium.platform.Definitions.StationBoardWithDetails

class SharedViewModel : ViewModel() {
    var stationBoardData: StationBoardWithDetails? = null
    var topBarShow by mutableStateOf(false)
    var topBarTitle by mutableStateOf("Platform")
    var topBarShowBack by mutableStateOf(false)
    var topBarActions by mutableStateOf<(@Composable RowScope.() -> Unit)?>(null)

    override fun onCleared() {
        super.onCleared()
        topBarShow = false
        topBarTitle = "Platform"
        topBarShowBack = false
        topBarActions = null
    }
}