package com.dmetz.unitconverter.ui.converter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dmetz.unitconverter.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConverterKeyboard(
    viewModel: ConverterViewModel,
    state: ConverterState,
    onClickSelectFromUnit: () -> Unit,
    onClickSelectToUnit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 8.dp, top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            SelectUnitButton(
                symbol = state.fromUnit.name,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                onClick = onClickSelectFromUnit,
                modifier = Modifier
                    .aspectRatio(4f)
                    .weight(1f)
            )
            IconButton(
                onClick = { viewModel.onAction(ConverterAction.SwapUnits) },
                modifier = Modifier.weight(0.2f)
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_swap_horiz_24),
                    contentDescription = "Swap Units",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            SelectUnitButton(
                symbol = state.toUnit.name,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                onClick = onClickSelectToUnit,
                modifier = Modifier
                    .aspectRatio(4f)
                    .weight(1f)
            )
        }

        KeyboardRow {
            ConverterButton(
                symbol = "AC",
                color = MaterialTheme.colorScheme.tertiaryContainer,
                onClick = {
                    viewModel.onAction(ConverterAction.Clear)
                }
            )
            ConverterButton(
                symbol = "( )",
                color = MaterialTheme.colorScheme.secondaryContainer,
                onClick = {
                    viewModel.onAction(ConverterAction.Parenthesis)
                }
            )
            ConverterButton(
                symbol = "^",
                color = MaterialTheme.colorScheme.secondaryContainer,
                onClick = {
                    viewModel.onAction(ConverterAction.Exponent)
                }
            )
            ConverterButton(
                symbol = "/",
                color = MaterialTheme.colorScheme.secondaryContainer,
                onClick = {
                    viewModel.onAction(ConverterAction.Divide)
                }
            )
        }
        KeyboardRow {
            ConverterButton(
                symbol = "7",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    3.dp
                ),
                onClick = {
                    viewModel.onAction(ConverterAction.Number(7))
                }
            )
            ConverterButton(
                symbol = "8",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    3.dp
                ),
                onClick = {
                    viewModel.onAction(ConverterAction.Number(8))
                }
            )
            ConverterButton(
                symbol = "9",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    3.dp
                ),
                onClick = {
                    viewModel.onAction(ConverterAction.Number(9))
                }
            )
            ConverterButton(
                symbol = "×",
                color = MaterialTheme.colorScheme.secondaryContainer,
                onClick = {
                    viewModel.onAction(ConverterAction.Multiply)
                }
            )
        }
        KeyboardRow {
            ConverterButton(
                symbol = "4",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    3.dp
                ),
                onClick = {
                    viewModel.onAction(ConverterAction.Number(4))
                }
            )
            ConverterButton(
                symbol = "5",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    3.dp
                ),
                onClick = {
                    viewModel.onAction(ConverterAction.Number(5))
                }
            )
            ConverterButton(
                symbol = "6",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    3.dp
                ),
                onClick = {
                    viewModel.onAction(ConverterAction.Number(6))
                }
            )
            ConverterButton(
                symbol = "-",
                color = MaterialTheme.colorScheme.secondaryContainer,
                onClick = {
                    viewModel.onAction(ConverterAction.Minus)
                }
            )
        }
        KeyboardRow {
            ConverterButton(
                symbol = "1",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    3.dp
                ),
                onClick = {
                    viewModel.onAction(ConverterAction.Number(1))
                }
            )
            ConverterButton(
                symbol = "2",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    3.dp
                ),
                onClick = {
                    viewModel.onAction(ConverterAction.Number(2))
                }
            )
            ConverterButton(
                symbol = "3",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    3.dp
                ),
                onClick = {
                    viewModel.onAction(ConverterAction.Number(3))
                }
            )
            ConverterButton(
                symbol = "+",
                color = MaterialTheme.colorScheme.secondaryContainer,
                onClick = {
                    viewModel.onAction(ConverterAction.Plus)
                }
            )
        }
        KeyboardRow {
            ConverterButton(
                symbol = "0",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    3.dp
                ),
                onClick = {
                    viewModel.onAction(ConverterAction.Number(0))
                }
            )
            ConverterButton(
                symbol = "•",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    3.dp
                ),
                onClick = {
                    viewModel.onAction(ConverterAction.Decimal)
                }
            )
            ConverterButton(
                symbol = painterResource(id = R.drawable.outline_backspace_24),
                desc = "Backspace",
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                onClick = { viewModel.onAction(ConverterAction.Delete) }
            )
            ConverterButton(
                symbol = "=",
                color = MaterialTheme.colorScheme.secondaryContainer,
                onClick = {
                    viewModel.onAction(ConverterAction.Equals)
                }
            )
        }
    }
}

@Composable
fun ColumnScope.KeyboardRow(
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        content()
    }
}
