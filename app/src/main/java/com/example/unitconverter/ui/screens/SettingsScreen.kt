package com.example.unitconverter.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.unitconverter.R
import com.example.unitconverter.preferences.AppPreferences
import com.example.unitconverter.preferences.PrefsRepository
import com.example.unitconverter.ui.converter.ConverterState
import com.example.unitconverter.ui.converter.ConverterViewModel
import com.example.unitconverter.ui.theme.ThemeMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalStdlibApi::class)
@Composable
fun SettingsScreen(
    viewModel: ConverterViewModel,
    title: String,
    state: ConverterState,
    prefsRepository: PrefsRepository,
    navBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                ),
                navigationIcon = {
                    IconButton(onClick = navBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth()
        ) {
            var expanded by remember { mutableStateOf(false) }
            Box {
                TextButton(
                    onClick = { expanded = true },
                    shape = RectangleShape,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    modifier = Modifier.height(64.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Icon(
                            painterResource(R.drawable.baseline_palette_24),
                            "Theme Icon"
                        )
                        Column {
                            Text(
                                text = "Theme",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                // text = prefsRepository.getTheme.collectAsState(initial = "AUTO").value,
                                text = prefsRepository.preferencesFlow.collectAsState(
                                    initial = AppPreferences()
                                ).value.themeMode.name,
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ThemeMode.values().forEach {
                        DropdownMenuItem(
                            text = { Text(text = it.name) },
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    prefsRepository.saveTheme(it)
                                }
                                expanded = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
