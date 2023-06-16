package com.dmetz.unitconverter.data.util

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.floor
import kotlin.math.log10

fun BigDecimal.setMinScale(scale: Int): BigDecimal {
    return this.setScale(
        kotlin.math.max(
            scale,
            if (this.abs() < BigDecimal.ONE) {
                -floor(
                    log10(
                        this.abs().remainder(BigDecimal.ONE).toDouble()
                    )
                ).toInt()
            } else {
                0
            }
        ),
        RoundingMode.HALF_EVEN
    )
}

/**
 * Remove all trailing zeroes
 */
fun BigDecimal.trim(): BigDecimal {
    return if (this.compareTo(BigDecimal.ZERO) == 0) BigDecimal.ZERO else this.stripTrailingZeros()
}
