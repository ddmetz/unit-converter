package com.dmetz.unitconverter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dmetz.unitconverter.ui.converter.ConversionExpressionText
import com.dmetz.unitconverter.ui.converter.ConverterKeyboard
import com.dmetz.unitconverter.ui.converter.ConverterState
import com.dmetz.unitconverter.ui.converter.ConverterViewModel
import com.dmetz.unitconverter.ui.menu.MainDropdownMenu

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    viewModel: ConverterViewModel,
    state: ConverterState,
    onClickSelectFromUnit: () -> Unit,
    onClickSelectToUnit: () -> Unit,
    onClickSettings: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 0.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            ) {
                var menuExpanded by remember { mutableStateOf(false) }
                IconButton(
                    onClick = { menuExpanded = true }
                ) {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = "Options",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    MainDropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        onClickSettings = onClickSettings,
                        onClickAbout = { } // TODO
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            ) {
                Spacer(modifier = Modifier.weight(1f, false))
                if (state.fromExpression.isEmpty()) {
                    ConversionExpressionText(
                        expression = state.placeholderFromExpression,
                        unit = state.fromUnit.shortName,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                } else {
                    ConversionExpressionText(
                        expression = state.fromExpression,
                        appendPartial = if (
                            state.fromExpression.isNotEmpty() &&
                            state.fromExpression.last() != '('
                        ) {
                            ")".repeat(state.numUnclosedParentheses)
                        } else {
                            ""
                        },
                        unit = state.fromUnit.shortName,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f, false))
                ConversionExpressionText(
                    expression = state.toNumber,
                    unit = state.toUnit.shortName,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
                Spacer(modifier = Modifier.weight(1f, false))
            }
            ConverterKeyboard(
                viewModel = viewModel,
                state = state,
                onClickSelectFromUnit = onClickSelectFromUnit,
                onClickSelectToUnit = onClickSelectToUnit
            )
        }
    }
}
