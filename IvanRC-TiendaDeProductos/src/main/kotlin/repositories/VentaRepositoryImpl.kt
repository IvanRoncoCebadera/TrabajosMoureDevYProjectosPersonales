package repositories

import models.*
import java.util.*

class VentaRepositoryImpl: VentaRepository<Venta, Int> {
    val ventas = mutableListOf<Venta>()

    /**
     * función que consigue todas las ventas
     * @param la lista de ventas encontrada
     */
    override fun listarTodasLasVentas(): List<Venta> {
        return ventas.toList()
    }

    /**
     * función que consigue todas las ventas del usuario especificado por su id
     * @param idUsuarioActual es el id del usuario cuyas ventas queremos
     * @param la lista de ventas encontrada
     */
    override fun listarTodasLasVentasPropias(idUsuarioActual: Int): List<Venta> {
        return ventas.filter { it.userID == idUsuarioActual }
    }

    /**
     * función que nos permite almacenar la venta
     * @param venta la venta que deseamos almacenar
     * @return la venta almacenada o nulo en caso de que se quiera almacenar la misma venta
     */
    override fun guardar(venta: Venta): Venta {
        ventas.add(venta)
        return venta
    }

}