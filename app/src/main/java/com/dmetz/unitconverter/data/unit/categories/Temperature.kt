package com.dmetz.unitconverter.data.unit.categories

import com.dmetz.unitconverter.data.unit.AbstractUnit
import com.dmetz.unitconverter.data.unit.UnitCategory
import com.dmetz.unitconverter.data.util.setMinScale
import com.dmetz.unitconverter.data.util.trim
import java.math.BigDecimal
import java.math.RoundingMode

internal val temperatureUnits: List<AbstractUnit> by lazy {
    listOf(
        object : AbstractUnit(
            name = "Celsius",
            group = UnitCategory.TEMPERATURE,
            shortName = "°C"
        ) {
            override fun convert(value: BigDecimal, unitTo: AbstractUnit): BigDecimal {
                return when (unitTo.name) {
                    "Fahrenheit" -> {
                        value
                            .setScale(1000, RoundingMode.HALF_EVEN)
                            .times(BigDecimal.valueOf(1.8))
                            .plus(BigDecimal(32))
                    }
                    "Kelvin" -> {
                        value
                            .setScale(1000, RoundingMode.HALF_EVEN)
                            .plus(BigDecimal.valueOf(273.15))
                    }
                    else -> value
                }
                    .setMinScale(3)
                    .trim()
            }
        },
        object : AbstractUnit(
            name = "Fahrenheit",
            group = UnitCategory.TEMPERATURE,
            shortName = "°F"
        ) {
            override fun convert(value: BigDecimal, unitTo: AbstractUnit): BigDecimal {
                return when (unitTo.name) {
                    "Celsius" -> {
                        value
                            .setScale(1000, RoundingMode.HALF_EVEN)
                            .minus(BigDecimal(32))
                            .times(BigDecimal(5))
                            .div(BigDecimal(9))
                    }
                    "Kelvin" -> {
                        value
                            .setScale(1000, RoundingMode.HALF_EVEN)
                            .minus(BigDecimal(32))
                            .times(BigDecimal(5))
                            .div(BigDecimal(9))
                            .add(BigDecimal.valueOf(273.15))
                    }
                    else -> value
                }
                    .setMinScale(3)
                    .trim()
            }
        },
        object : AbstractUnit(
            name = "Kelvin",
            group = UnitCategory.TEMPERATURE,
            shortName = "K"
        ) {
            override fun convert(value: BigDecimal, unitTo: AbstractUnit): BigDecimal {
                return when (unitTo.name) {
                    "Celsius" -> {
                        value
                            .setScale(1000, RoundingMode.HALF_EVEN)
                            .minus(BigDecimal(273.15))
                    }
                    "Fahrenheit" -> {
                        value
                            .setScale(1000, RoundingMode.HALF_EVEN)
                            .minus(BigDecimal.valueOf(273.15))
                            .times(BigDecimal.valueOf(1.8))
                            .plus(BigDecimal(32))
                    }
                    else -> value
                }
                    .setMinScale(3)
                    .trim()
            }
        }
    )
}
