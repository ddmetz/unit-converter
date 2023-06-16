package com.example.unitconverter.data.unit

import com.example.unitconverter.data.util.setMinScale
import com.example.unitconverter.data.util.trim
import java.math.BigDecimal
import java.math.RoundingMode

class StandardUnit(
    name: String,
    category: UnitCategory,
    shortName: String,
    standardUnit: BigDecimal
) : AbstractUnit(
    name,
    category,
    shortName,
    standardUnit
) {
    override fun convert(value: BigDecimal, unitTo: AbstractUnit): BigDecimal {
        return this
            .standardUnit
            .setScale(1000, RoundingMode.HALF_EVEN)
            .multiply(value.setScale(1000, RoundingMode.HALF_EVEN))
            .div(unitTo.standardUnit.setScale(1000, RoundingMode.HALF_EVEN))
            .setMinScale(10)
            .trim()
    }
}
