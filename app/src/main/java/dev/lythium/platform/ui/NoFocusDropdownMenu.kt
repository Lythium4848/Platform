package dev.lythium.platform.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun NoFocusDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val focusManager = LocalFocusManager.current
    val dismiss = {
        focusManager.clearFocus()
        onDismissRequest()
    }

    Box(
        modifier = Modifier
            .zIndex(if (expanded) 1f else 0f)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
    ) {
        if (expanded) {
            Column(content = content)
            DisposableEffect(Unit) {
                onDispose {
                    dismiss()
                }
            }
        }
    }
}