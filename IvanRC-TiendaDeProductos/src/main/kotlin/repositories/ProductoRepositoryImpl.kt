package repositories

import models.Producto

object ProductoRepositoryImpl: ProductoRepository {
    private val almacen = mutableMapOf<Int, Producto>(
        1 to Producto(1, "Residen evil 4", 70.0, 99),
        2 to Producto(2, "Residen evil 3 Remake", 70.0, 99),
        3 to Producto(3, "Portatil Asus", 70.0, 99),
        4 to Producto(4, "Egoland", 70.0, 99),
        5 to Producto(5, "Minecraft", 70.0, 99),
        6 to Producto(6, "Hi-Fi Rush", 70.0, 99),
    )

    /**
     * función que lista todos los productos existentes en el almacen
     * @return una lista con todos los productos
     */
    override fun listarTodo(): List<Producto> {
        return almacen.values.toList()
    }

    /**
     * función que lista todos los productos existentes en el almacen
     * @param disponible es para decidir si queremos sacar a todos los productos disponibles o a los no disponibles
     * @return una lista con todos los productos
     */
    override fun listarTodosLosProductosByDisponible(disponible: Boolean): List<Producto> {
        return almacen.values.filter { it.disponible == disponible }.toList()
    }

    /**
     * función que busca los productos que contengán el nombre que se introducio
     * @param nombre es el nombre por el que queremos buscar
     * @return una lista con todos los productos que contengan el nombre introducido
     */
    override fun buscarPorNombre(nombre: String): List<Producto> {
        return almacen.values.filter { it.nombre.lowercase().contains(nombre.lowercase()) }
    }

    /**
     * función que busca los productos que tengán el mismo id que el introducido
     * @param id es el id del producto a buscar
     * @return una lista con todos los productos que tengan el id introducido
     */
    override fun buscarPorID(id: Int): Producto? {
        return almacen.values.find { it.id == id }
    }

    /**
     * funció que me permite eliminar un producto, buscando a partir de su id
     * @param id es el id del producto a buscar
     * @return null si no se encuentra ningún producto con ese id, o el producto borrado en caso de que haya uno, que sería el primero encontrado
     */
    override fun eliminar(id: Int): Producto? {
        var producto: Producto? = null
        (buscarPorID(id))?.let{
            producto = it
            almacen.remove(producto!!.id)
        }
        return producto
    }

    /**
     * función que me permite añadir un nuevo producto
     * @param prod es el nuevo producto cuyos datos queremos añadir
     * @return el prod cuyos datos hemos añadido al almacen, o nulo, si ya existia
     */
    override fun añadir(prod: Producto): Producto? {
        if(buscarPorID(prod.id) == null){
            almacen[prod.id] = prod
            return prod
        }
        return null
    }

    /**
     * función que nos permite actualizar un producto del almacen
     * @param entity los nuevos datos del producto a actualizar
     * @return el producto actualizado, o nulo si no se onsiguio actualizar
     */
    override fun actualizar(entity: Producto): Producto? {
        (buscarPorID(entity.id))?.let{
            almacen.remove(it.id)
            almacen[entity.id] = entity
            return entity
        }
        return null
    }
}