package com.dmetz.unitconverter.data.unit

import java.math.BigDecimal

abstract class AbstractUnit(
    val name: String,
    val group: UnitCategory,
    val shortName: String,
    val standardUnit: BigDecimal = BigDecimal(1)
) {
    abstract fun convert(
        value: BigDecimal,
        unitTo: AbstractUnit
    ): BigDecimal
}
