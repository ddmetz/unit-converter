package com.dmetz.unitconverter.ui.converter

import com.dmetz.unitconverter.data.unit.AbstractUnit
import com.dmetz.unitconverter.data.unit.categories.temperatureUnits

data class ConverterState(
    val placeholderFromExpression: String = "0",
    val fromExpression: String = "",
    val toNumber: String = "",
    val numUnclosedParentheses: Int = 0,
    val fromUnit: AbstractUnit = temperatureUnits[0],
    val toUnit: AbstractUnit = temperatureUnits[1]
)
