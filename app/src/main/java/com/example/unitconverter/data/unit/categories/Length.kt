package com.example.unitconverter.data.unit.categories

import com.example.unitconverter.data.unit.AbstractUnit
import com.example.unitconverter.data.unit.StandardUnit
import com.example.unitconverter.data.unit.UnitCategory
import java.math.BigDecimal

internal val lengthUnits: List<AbstractUnit> by lazy {
    listOf(
        StandardUnit("Nanometer", UnitCategory.LENGTH, "nm", BigDecimal(1.0E-9)),
        StandardUnit("Micrometer", UnitCategory.LENGTH, "μm", BigDecimal(1.0E-6)),
        StandardUnit("Millimeter", UnitCategory.LENGTH, "mm", BigDecimal(1.0E-3)),
        StandardUnit("Centimeter", UnitCategory.LENGTH, "cm", BigDecimal(1.0E-2)),
        StandardUnit("Decimeter", UnitCategory.LENGTH, "dm", BigDecimal(1.0E-1)),
        StandardUnit("Meter", UnitCategory.LENGTH, "m", BigDecimal(1.0)),
        StandardUnit("Kilometer", UnitCategory.LENGTH, "km", BigDecimal(1.0E+3)),
        StandardUnit("Inch", UnitCategory.LENGTH, "in", BigDecimal(1)),
        StandardUnit(
            "Foot",
            UnitCategory.LENGTH,
            "ft",
            BigDecimal.ONE.setScale(1000).div(BigDecimal("3.280839895"))
        ),
        StandardUnit(
            "Yard",
            UnitCategory.LENGTH,
            "yd",
            BigDecimal.ONE.setScale(1000).div(BigDecimal("1.093613298"))
        ),
        StandardUnit(
            "Mile",
            UnitCategory.LENGTH,
            "mi",
            BigDecimal.ONE.setScale(1000).div(BigDecimal("0.0006213711"))
        ),
        StandardUnit("Nautical Mile", UnitCategory.LENGTH, "nmi", BigDecimal(1852))
    )
}
