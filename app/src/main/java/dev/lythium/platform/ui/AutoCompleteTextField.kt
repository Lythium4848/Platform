package dev.lythium.platform.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import dev.lythium.platform.Station

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoCompleteTextField(
    items: List<Station>,
    label: String,
    modifier: Modifier = Modifier,
    onSelect: (Station) -> Unit = {},
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    var text by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    val filteredItems = items.filter {
        it.name.contains(text, ignoreCase = true)
    }.take(5)
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                isDropdownExpanded = it.isNotEmpty()
            },
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            colors = colors
        )

        NoFocusDropdownMenu(
            expanded = isDropdownExpanded && filteredItems.isNotEmpty(),
            onDismissRequest = { isDropdownExpanded = false },
        ) {
            filteredItems.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        text = label.name
                        isDropdownExpanded = false
                        onSelect(label)
                    },
                    text = { Text(text = label.name) }
                )
            }
        }
    }
}