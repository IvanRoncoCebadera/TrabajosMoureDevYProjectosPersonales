package validator

import exceptions.ProductoBadRequestException
import exceptions.ProductoNotFoundException
import exceptions.UsuarioBadRequestException
import exceptions.VentaCancelledException
import models.Producto
import models.Usuario
import models.Venta
import repositories.ProductoRepository

fun Producto.validar(): Boolean{
    require(nombre.isNotEmpty()){
        throw ProductoBadRequestException("Fallo de datos: El nombre $nombre, no puede estar vacio.")
    }
    require(precioUnitario >= 0.0){
        throw ProductoBadRequestException("Fallo de datos: El precio unitario $precioUnitario, no puede ser menor que  0.0.")
    }
    require(stock >= 0){
        throw ProductoBadRequestException("Fallo de datos: El stock $stock, no puede ser menor que 0.")
    }
    return true
}

fun Usuario.validar(list: List<Usuario>): Boolean{
    require(username.isNotEmpty()){
        throw UsuarioBadRequestException("Fallo de datos: El username $username, no puede estar vacio.")
    }
    require(!list.map { it.username }.contains(username)){
        throw UsuarioBadRequestException("Fallo de datos: El username $username, ya existe.")
    }
    require(password.isNotEmpty()){
        throw UsuarioBadRequestException("Fallo de datos: El password $password, no puede estar vacio.")
    }
    require(!list.map { it.password }.contains(password)){
        throw UsuarioBadRequestException("Fallo de datos: El password $password, ya existe.")
    }
    return true
}

fun Venta.validar(prodRepos: ProductoRepository, items: Map<Int, Int>): Boolean{
    require(this.lineas.isNotEmpty()){
        throw VentaCancelledException("Fallo con la cesta de productos: NO hay ningún producto a comprar, por lo que se cancelo la compra.")
    }
    items.forEach{item ->
        val producto = prodRepos.buscarPorID(item.key)?: throw ProductoNotFoundException("Fallo al buscar un producto: No se encontro el producto buscado.")
        require(item.value <= producto.stock){
            throw VentaCancelledException("Fallo con el stock del producto: No hay tanto stock del producto: ${producto}, com se desea comprar.")
        }
    }
    return true
}