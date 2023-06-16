package com.dmetz.unitconverter.data.util

import kotlin.String

fun String.trimOperators(): String {
    return this.dropLastWhile {
        it.isOperator()
    }
}

val operatorList = listOf<Char>(
    '+',
    '-',
    '*',
    '/',
    '^',
    '('
)
private fun Char.isOperator(): Boolean {
    return operatorList.contains(this)
}
