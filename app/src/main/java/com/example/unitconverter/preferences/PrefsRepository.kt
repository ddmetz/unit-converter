package com.example.unitconverter.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.unitconverter.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class AppPreferences(
    val themeMode: ThemeMode = ThemeMode.AUTO
//    val recentFromUnit: String = "Celsius",
//    val recentToUnit: String = "Fahrenheit",
)

class PrefsRepository(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
    }

    private object Keys {
        val THEME_MODE = stringPreferencesKey("theme_mode")
//        val RECENT_FROM_UNIT = stringPreferencesKey("recent_from_unit")
//        val RECENT_TO_UNIT = stringPreferencesKey("recent_to_unit")
    }

    val preferencesFlow: Flow<AppPreferences> = context.dataStore.data.map { preferences ->
        val themeMode = preferences[Keys.THEME_MODE]?.let {
            ThemeMode.valueOf(it)
        } ?: ThemeMode.AUTO
//        val recentFromUnit = preferences[Keys.RECENT_FROM_UNIT] ?: "Celsius"
//        val recentToUnit = preferences[Keys.RECENT_TO_UNIT] ?: "Fahrenheit"

        AppPreferences(
            themeMode
//            recentFromUnit,
//            recentToUnit
        )
    }

    val getTheme: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[Keys.THEME_MODE] ?: ThemeMode.AUTO.name
    }

    suspend fun saveTheme(themeMode: ThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[Keys.THEME_MODE] = themeMode.name
        }
    }

//    suspend fun saveRecentFromUnit(unit: AbstractUnit) {
//        context.dataStore.edit { preferences ->
//            preferences[Keys.RECENT_FROM_UNIT] = unit.name
//        }
//    }
//
//    suspend fun saveRecentToUnit(unit: AbstractUnit) {
//        context.dataStore.edit { preferences ->
//            preferences[Keys.RECENT_TO_UNIT] = unit.name
//        }
//    }
}
