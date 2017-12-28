@file:JvmName("UtilsKt")
@file:JvmMultifileClass

package crossdevelop.com.cryptocoin.widgets

import java.math.BigDecimal

/**
 * Created by Ian Cross on 12/28/17.
 */

/**
 * shortens a Double to the number of given decimal points
 */
fun shortenDouble(number: Double, decimalPoints: Int) =
        BigDecimal(number).setScale(decimalPoints, BigDecimal.ROUND_HALF_UP)

