package com.dmetz.unitconverter.ui.menu

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onClickSettings: () -> Unit,
    onClickAbout: () -> Unit
) {
    DropdownMenu(expanded = expanded, onDismissRequest = onDismissRequest) {
        DropdownMenuItem(
            text = { Text("Settings") },
            onClick = {
                onClickSettings()
                onDismissRequest()
            }
        )
        DropdownMenuItem(
            text = { Text("About") },
            onClick = {
                onClickAbout()
                onDismissRequest()
            }
        )
    }
}
