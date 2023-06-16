package com.example.unitconverter.ui.converter

import com.example.unitconverter.data.unit.AbstractUnit
import com.example.unitconverter.data.unit.UnitCategory

sealed class ConverterAction {
    data class Number(val number: Int) : ConverterAction()
    object Clear : ConverterAction()
    object Delete : ConverterAction()
    object SwapUnits : ConverterAction()
    object Decimal : ConverterAction()
    object Equals : ConverterAction()
    object Multiply : ConverterAction()
    object Divide : ConverterAction()
    object Plus : ConverterAction()
    object Minus : ConverterAction()
    object Exponent : ConverterAction()
    object Parenthesis : ConverterAction()

    data class SelectFromUnit(val abstractUnit: AbstractUnit) : ConverterAction()
    data class SelectToUnit(val abstractUnit: AbstractUnit) : ConverterAction()

    data class SelectCategory(val unitCategory: UnitCategory) : ConverterAction()
}
