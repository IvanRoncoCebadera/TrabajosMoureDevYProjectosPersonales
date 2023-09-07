package models

import locales.toLocalMoney

data class LVenta(val ventaId: Int, val lineaId: Int, val productoID: Int, val cantidad: Int = 1,  val precioUnitario: Double) {
    val total: Double
        get() = cantidad * precioUnitario

    fun toLocalString(): String{
        return "LVenta(lineaId=$lineaId, productoID=$productoID, cantidad=$cantidad, precioUnitario=${precioUnitario.toLocalMoney()}, total=${total.toLocalMoney()})"
    }
}
