package factories

import general.General
import general.General.Companion.introducirContenido
import general.General.Companion.introducirNumero
import general.General.Companion.introducirNumeroDecimal
import models.Producto

object ProductoFactory{
    /**
     * función que permite la creación de un producto a mano
     * @return el rpoducto creado a mano
     */
    fun crearProducto(): Producto {
        println("Creé el producto a añadir en el almacén:")
        println("Ahora introduzca el id del producto:")
        val id = introducirNumero(1, 9999).toInt()

        println("Ahora introduzca el nombre del producto:")
        val nombre = introducirContenido()

        println("Ahora introduzca el precio unitario del producto:")
        val precioUnitario = introducirNumeroDecimal(0.0).toDouble()

        println("Ahora introduzca el stock del producto:")
        val stock = introducirNumero(0, 9999).toInt()

        return Producto(id, nombre, precioUnitario, stock)
    }
}