package com.dmetz.unitconverter.data.unit

import com.dmetz.unitconverter.data.unit.categories.lengthUnits
import com.dmetz.unitconverter.data.unit.categories.temperatureUnits

val unitCategoryMap = mapOf<UnitCategory, List<AbstractUnit>>(
    UnitCategory.TEMPERATURE to temperatureUnits,
    UnitCategory.LENGTH to lengthUnits
)

val unitCategoryList = UnitCategory.values().toList()

// val allUnits = unitCategoryMap.values.flatten()
