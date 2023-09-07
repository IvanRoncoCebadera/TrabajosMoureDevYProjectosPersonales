package repositories

import models.Producto

interface ProductoRepository: CrudRepository<Producto, Int> {
    fun listarTodosLosProductosByDisponible(disponible: Boolean): List<Producto>
    fun buscarPorID(id: Int): Producto?
    fun buscarPorNombre(nombre: String): List<Producto>
}