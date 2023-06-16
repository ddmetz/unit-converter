package com.example.unitconverter.ui.converter

import androidx.lifecycle.ViewModel
import com.example.unitconverter.data.unit.AbstractUnit
import com.example.unitconverter.data.unit.UnitCategory
import com.example.unitconverter.data.unit.unitCategoryMap
import com.example.unitconverter.data.util.trimOperators
import com.ezylang.evalex.Expression
import java.lang.Exception
import java.math.BigDecimal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ConverterViewModel : ViewModel() {

    private var _state = MutableStateFlow(
        ConverterState(
            toNumber = "32"
        )
    )
    val state = _state.asStateFlow()

    fun onAction(action: ConverterAction) {
        when (action) {
            is ConverterAction.Number -> enterNumber(action.number)
            is ConverterAction.Delete -> delete()
            is ConverterAction.Clear -> {
                _state.update {
                    it.copy(
                        fromExpression = "",
                        toNumber = updateConversion(""),
                        numUnclosedParentheses = 0
                    )
                }
            }
            is ConverterAction.SwapUnits -> swapUnits()
            is ConverterAction.Decimal -> enterDecimal()
            is ConverterAction.Equals -> inputEquals()
            is ConverterAction.Plus -> enterPlus()
            is ConverterAction.Minus -> enterMinus()
            is ConverterAction.Multiply -> enterMultiply()
            is ConverterAction.Divide -> enterDivide()
            is ConverterAction.Exponent -> enterExponent()
            is ConverterAction.Parenthesis -> enterParenthesis()
            is ConverterAction.SelectFromUnit -> selectFromUnit(action.abstractUnit)
            is ConverterAction.SelectToUnit -> selectToUnit(action.abstractUnit)
            is ConverterAction.SelectCategory -> selectCategory(action.unitCategory)
        }
    }

    private fun updateConversion(fromNumber: String): String {
        val actualExpression = fromNumber.trimOperators() + ")".repeat(
            _state.value.numUnclosedParentheses
        )
        return if (fromNumber.isNotEmpty()) {
            try {
                _state.value.fromUnit.convert(
                    Expression(
                        actualExpression
                    ).evaluate().numberValue,
                    _state.value.toUnit
                ).toPlainString()
            } catch (e: Exception) {
                _state.value.toNumber
            }
        } else {
            _state.value.fromUnit.convert(
                Expression(
                    _state.value.placeholderFromExpression.trimOperators()
                ).evaluate().numberValue,
                _state.value.toUnit
            ).toPlainString()
        }
    }

    private fun updateConversion(
        fromNumber: String,
        fromUnit: AbstractUnit,
        toUnit: AbstractUnit
    ): String {
        return if (fromNumber.isNotEmpty()) {
            fromUnit.convert(BigDecimal(fromNumber), toUnit).toString()
        } else {
            fromUnit.convert(BigDecimal(_state.value.placeholderFromExpression), toUnit).toString()
        }
    }

    private fun delete() {
        var newNumUnclosedParenthesis = _state.value.numUnclosedParentheses
        if (_state.value.fromExpression.lastOrNull() == '(') {
            newNumUnclosedParenthesis--
        }
        if (_state.value.fromExpression.lastOrNull() == ')') {
            newNumUnclosedParenthesis++
        }
        val newFromNum = _state.value.fromExpression.dropLast(1)
        if (state.value.fromExpression.isNotBlank()) {
            _state.update {
                it.copy(
                    fromExpression = newFromNum,
                    toNumber = updateConversion(newFromNum),
                    numUnclosedParentheses = newNumUnclosedParenthesis
                )
            }
        }
    }

    private fun inputEquals() {
        if (_state.value.fromExpression.isEmpty()) {
            return
        }
        val actualExpression = _state.value.fromExpression.trimOperators() + ")".repeat(
            _state.value.numUnclosedParentheses
        )
        try {
            val newFromNum = Expression(actualExpression)
                .evaluate()
                .numberValue
                .toPlainString()
            _state.update {
                it.copy(
                    fromExpression = newFromNum,
                    toNumber = updateConversion(newFromNum),
                    numUnclosedParentheses = 0
                )
            }
        } catch (_: Exception) {
        }
    }

    private fun enterNumber(number: Int) {
        val newFromNum = _state.value.fromExpression + number
        _state.update {
            it.copy(
                fromExpression = newFromNum,
                toNumber = updateConversion(newFromNum)
            )
        }
    }

    private fun enterDecimal() {
        val newFromNum = _state.value.fromExpression + "."
        _state.update {
            it.copy(
                fromExpression = newFromNum,
                toNumber = updateConversion(newFromNum)
            )
        }
    }

    private fun enterPlus() {
        val newFromNum = _state.value.fromExpression + "+"
        _state.update {
            it.copy(
                fromExpression = newFromNum,
                toNumber = updateConversion(newFromNum)
            )
        }
    }

    private fun enterMinus() {
        val newFromNum = _state.value.fromExpression + "-"
        _state.update {
            it.copy(
                fromExpression = newFromNum,
                toNumber = updateConversion(newFromNum)
            )
        }
    }

    private fun enterMultiply() {
        val newFromNum = _state.value.fromExpression + "*"
        _state.update {
            it.copy(
                fromExpression = newFromNum,
                toNumber = updateConversion(newFromNum)
            )
        }
    }

    private fun enterDivide() {
        val newFromNum = _state.value.fromExpression + "/"
        _state.update {
            it.copy(
                fromExpression = newFromNum,
                toNumber = updateConversion(newFromNum)
            )
        }
    }

    private fun enterExponent() {
        val newFromNum = _state.value.fromExpression + "^"
        _state.update {
            it.copy(
                fromExpression = newFromNum,
                toNumber = updateConversion(newFromNum)
            )
        }
    }

    private fun enterParenthesis() {
        val newString: String
        val newNumUnclosedParentheses: Int
        if (_state.value.numUnclosedParentheses == 0 || _state.value.fromExpression.last() == '(') {
            newString = _state.value.fromExpression + "("
            newNumUnclosedParentheses = _state.value.numUnclosedParentheses + 1
        } else {
            newString = _state.value.fromExpression + ")"
            newNumUnclosedParentheses = _state.value.numUnclosedParentheses - 1
        }

        _state.update {
            it.copy(
                fromExpression = newString,
                toNumber = updateConversion(newString),
                numUnclosedParentheses = newNumUnclosedParentheses
            )
        }
    }

    private fun swapUnits() {
        this.onAction(ConverterAction.Equals)
        if (_state.value.fromExpression.isNotEmpty()) {
            _state.update {
                it.copy(
                    fromExpression = _state.value.toNumber,
                    toNumber = _state.value.fromExpression,
                    fromUnit = _state.value.toUnit,
                    toUnit = _state.value.fromUnit
                )
            }
        } else {
            _state.update {
                it.copy(
                    toNumber = updateConversion("", _state.value.toUnit, _state.value.fromUnit),
                    fromUnit = _state.value.toUnit,
                    toUnit = _state.value.fromUnit
                )
            }
        }
    }

    private fun selectFromUnit(abstractUnit: AbstractUnit) {
        val newResult = updateConversion("", abstractUnit, _state.value.toUnit)
        _state.update {
            it.copy(
                toNumber = newResult,
                fromUnit = abstractUnit
            )
        }
    }

    private fun selectToUnit(abstractUnit: AbstractUnit) {
        val newResult = updateConversion("", _state.value.fromUnit, abstractUnit)
        _state.update {
            it.copy(
                fromExpression = "",
                toNumber = newResult,
                toUnit = abstractUnit
            )
        }
    }

    private fun selectCategory(unitCategory: UnitCategory) {
        val newFromUnit = unitCategoryMap[unitCategory]?.get(0) ?: _state.value.fromUnit
        val newToUnit = unitCategoryMap[unitCategory]?.get(1) ?: _state.value.toUnit
        val newResult = updateConversion("", newFromUnit, newToUnit)
        _state.update {
            it.copy(
                fromUnit = newFromUnit,
                toUnit = newToUnit,
                fromExpression = "",
                toNumber = newResult
            )
        }
    }
}
