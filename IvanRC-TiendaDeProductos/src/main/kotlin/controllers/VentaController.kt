package controllers

import exceptions.ProductoNotFoundException
import models.LVenta
import models.Venta
import repositories.ProductoRepository
import repositories.VentaRepository
import validator.validar

class VentaController(
    private val ventaRepo: VentaRepository<Venta, Int>,
    private val productoRepo: ProductoRepository
) {
    /**
     * función que consigue todas las ventas
     * @param la lista de ventas encontrada
     */
    fun listarTodasLasVentas(): List<Venta> {
        return ventaRepo.listarTodasLasVentas()
    }

    /**
     * función que consigue todas las ventas del usuario especificado por su id
     * @param idUsuarioActual es el id del usuario cuyas ventas queremos
     * @param la lista de ventas encontrada
     */
    fun listarTodasLasVentasPropias(idUsuarioActual: Int): List<Venta> {
        return ventaRepo.listarTodasLasVentasPropias(idUsuarioActual)
    }

    /**
     * función que nos permite almacenar la venta
     * @param venta la venta que deseamos almacenar
     * @return la venta almacenada o nulo en caso de que se quiera almacenar la misma venta
     */
    fun guardar(userId: Int, items: Map<Int, Int>): Venta {
        //Creamos la venta
        val venta = Venta(
            id = (ventaRepo.listarTodasLasVentas().maxOfOrNull { it.id }?:0)+1,
            userID = userId
        )
        //Creamos y añadimos todas las lineas de venta
        añadirLineas(items, venta)
        //Validamos la venta
        venta.validar(productoRepo, items)
        //Actualizamos el stock
        actualizarStock(productoRepo, items)
        return ventaRepo.guardar(venta)
    }

    /**
     * función que nos permite actualizar el stock de los productos
     * @param productoRepo el repositorio de productos con acceso al almacen de productos
     * @param items un mapa con la clave del producto deseada, y la cantidad el mismo
     */
    private fun actualizarStock(productoRepo: ProductoRepository, items: Map<Int, Int>) {
        items.forEach{item ->
            val producto = productoRepo.buscarPorID(item.key)?: throw ProductoNotFoundException("Fallo al buscar un producto: No se encontro el producto buscado.")
            productoRepo.actualizar(producto.copy(stock = producto.stock-item.value))
        }
    }

    /**
     * función que nos permite crear las lineas de venta, y añadirlas a la venta
     * @param items un mapa con la clave del producto deseada, y la cantidad el mismo
     * @param venta la venta a la que queremos añadir las lineas de venta
     */
    private fun añadirLineas(items: Map<Int, Int>, venta: Venta) {
        items.forEach{item ->
            val producto = productoRepo.buscarPorID(item.key)?: throw ProductoNotFoundException("Fallo al buscar producto: No se encontró ningún producto con el id pedido.")
            val linea = LVenta(
                ventaId = venta.id,
                lineaId = venta.nextLineaID,
                productoID = producto.id,
                precioUnitario = producto.precioUnitario,
                cantidad = item.value
            )
            venta.añadirLinea(linea)
        }
    }
}