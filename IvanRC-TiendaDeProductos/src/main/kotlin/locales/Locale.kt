package locales

import java.text.NumberFormat
import java.util.Locale

fun Double.toLocalMoney(): String{
    return NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(this)
}