package com.example.unitconverter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.unitconverter.preferences.AppPreferences
import com.example.unitconverter.preferences.PrefsRepository
import com.example.unitconverter.ui.converter.ConverterViewModel
import com.example.unitconverter.ui.screens.MainScreen
import com.example.unitconverter.ui.screens.SelectUnitMode
import com.example.unitconverter.ui.screens.SelectUnitScreen
import com.example.unitconverter.ui.screens.SettingsScreen
import com.example.unitconverter.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefsRepository = PrefsRepository(applicationContext)
        setContent {
            val appPrefs = prefsRepository
                .preferencesFlow
                .collectAsState(initial = AppPreferences())
                .value
            CalculatorTheme(
                themeMode = appPrefs.themeMode
            ) {
                val navController = rememberNavController()

                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "unit_converter") {
                        navigation(
                            startDestination = "main",
                            route = "unit_converter"
                        ) {
                            composable("main") { entry ->
                                val viewModel = entry.sharedViewModel<ConverterViewModel>(
                                    navController
                                )
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                MainScreen(
                                    viewModel = viewModel,
                                    state = state,
                                    onClickSelectFromUnit = {
                                        navController.navigate("select_from_unit")
                                    },
                                    onClickSelectToUnit = {
                                        navController.navigate("select_to_unit")
                                    },
                                    onClickSettings = { navController.navigate("settings") }
                                )
                            }
                            composable("settings") { entry ->
                                val viewModel = entry.sharedViewModel<ConverterViewModel>(
                                    navController
                                )
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                SettingsScreen(
                                    viewModel = viewModel,
                                    state = state,
                                    title = "Settings",
                                    prefsRepository = prefsRepository
                                ) {
                                    navController.popBackStack()
                                }
                            }
                            composable("select_from_unit") { entry ->
                                val viewModel = entry.sharedViewModel<ConverterViewModel>(
                                    navController
                                )
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                SelectUnitScreen(
                                    viewModel = viewModel,
                                    state = state,
                                    title = "Select unit to convert from",
                                    mode = SelectUnitMode.FROM
                                ) {
                                    navController.popBackStack()
                                }
                            }
                            composable("select_to_unit") { entry ->
                                val viewModel = entry.sharedViewModel<ConverterViewModel>(
                                    navController
                                )
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                SelectUnitScreen(
                                    viewModel = viewModel,
                                    state = state,
                                    title = "Select unit to convert to",
                                    mode = SelectUnitMode.TO
                                ) {
                                    navController.popBackStack()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
        navController: NavHostController
    ): T {
        val navGraphRoute = destination.parent?.route ?: return viewModel()
        val parentEntry = remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }
        return viewModel(parentEntry)
    }
}
