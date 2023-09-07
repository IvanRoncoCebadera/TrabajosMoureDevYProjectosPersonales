package controllers

import exceptions.ProductoAlreadyExistingException
import exceptions.ProductoNotFoundException
import models.Producto
import repositories.ProductoRepository
import validator.validar

class ProductoController(
    val repository: ProductoRepository
) {
    /**
     * función que llama al repositorio para conseguir todos los productos
     * @return la lista completa de todos los productos
     */
    fun listarTodosLosProductos(): List<Producto> {
        return repository.listarTodo()
    }

    /**
     * función que llama al repositorio para conseguir todos los productos
     * @param disponible es para decidir si queremos sacar a todos los productos disponibles o a los no disponibles
     * @return la lista completa de todos los productos
     */
    fun listarTodosLosProductosByDsiponible(disponible: Boolean): List<Producto>{
        return repository.listarTodosLosProductosByDisponible(disponible)
    }

    /**
     * función que llama al repo para que busque los productos que contengán el nombre que se introducio
     * @param nombre es el nombre por el que queremos buscar
     * @return una lista con todos los productos que contengan el nombre introducido
     */
    fun buscarPorNombre(nombre: String): List<Producto> {
        return repository.buscarPorNombre(nombre)
    }

    /**
     * función que llama al repo para que busque los productos que tengán el mismo id que el introducido
     * @param id es el id del producto a buscar
     * @throws ProductoNotFoundException si no se encontro al producto buscado
     * @return una lista con todos los productos que tengan el id introducido
     */
    fun buscarPorID(id: Int): Producto {
        return repository.buscarPorID(id)?: throw ProductoNotFoundException("Fallo al buscar producto: EL rpoducto que se deseaba buscar no se encontró.")
    }

    /**
     * función que llama al repositorio para añadir los datos de un producto al almacen
     * @param prod el producto cuyos datos vamos a incluir al almacen
     * @throws ProductoAlreadyExistingException si se intento añadir un producto ya existente
     * @return el producto cuyos datos hemos incluido en el almacén
     */
    fun añadirProducto(prod: Producto): Producto{
        prod.validar()
        return repository.añadir(prod)?: throw ProductoAlreadyExistingException("Fallo al añadir producto: El producto que desea añadir, ya existe.")
    }

    /**
     * función que llama al repositorio para actualizar los datos de un producto al almacen
     * @param prod el producto cuyos datos vamos a incluir al almacen
     * @throws ProductoNotFoundException si no se encontro al producto buscado
     * @return el producto cuyos datos hemos incluido en el almacén
     */
    fun actualizarProducto(producto: Producto): Producto{
        producto.validar()
        return repository.actualizar(producto)?: throw ProductoNotFoundException("Fallo al buscar producto: EL rpoducto que se deseaba actualizar no se encontró.")
    }

    /**
     * función que llama al repositorio para eliminar un producto del almacén
     * @param id es el id del producto a eliminar
     * @return el producto eliminado
     * @throws ProductoNotFoundException si no hay producto con el id facilitado
     */
    fun eliminarProducto(id: Int): Producto{
        return repository.eliminar(id)?: throw ProductoNotFoundException("Fallo al buscar producto: EL rpoducto que se deseaba eliminar no se encontró.")
    }
}