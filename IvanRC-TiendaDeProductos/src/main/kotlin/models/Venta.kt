package models

import locales.toLocalMoney
import java.time.LocalDate

data class Venta(val id: Int, val lineas: MutableList<LVenta> = mutableListOf(), val fecha: LocalDate = LocalDate.now(), val userID: Int){
    val nextLineaID: Int
        get() = (lineas.maxOfOrNull { it.lineaId }?: 0) + 1

    private val IVA = 21
    private val precioTotalSinIVA
            : Double
        get() = lineas.map { it.total }.sum()

    fun mostrarVenta(){
        println("Los productos acquiridos han sido:")
        lineas.forEach{ println(it.toLocalString()) }
        println()
        println("El precio total sin IVA, es de: ${precioTotalSinIVA.toLocalMoney()}")
        println("El precio total con un IVA del $IVA%, es de: ${(precioTotalSinIVA + precioTotalSinIVA*IVA/10024).toLocalMoney()}")
    }

    fun añadirLinea(linea: LVenta) {
        lineas.add(linea)
    }
}