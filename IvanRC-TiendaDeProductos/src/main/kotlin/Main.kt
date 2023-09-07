import controllers.ProductoController
import controllers.UsuarioController
import controllers.VentaController
import enums.RolDeUsuario
import factories.ProductoFactory.crearProducto
import factories.UsuarioFactory.crearUsuario
import factories.UsuarioFactory.seleccionarRol
import general.General.Companion.introducirContenido
import general.General.Companion.introducirNumero
import models.Producto
import models.Usuario
import models.Venta
import repositories.ProductoRepositoryImpl
import repositories.UsuarioRepositoryImpl
import repositories.VentaRepositoryImpl
import java.lang.Exception
import kotlin.system.exitProcess

private val userController = UsuarioController(UsuarioRepositoryImpl)
private val productoController = ProductoController(ProductoRepositoryImpl)
private val ventaController = VentaController(VentaRepositoryImpl(), ProductoRepositoryImpl)

var usuarioActual: Usuario? = null

fun main() {
    while(true) {
        do {
            println("Seleccione la opción que deseé:")
            when (seleccionarUsuarioActual()) {
                1 -> {
                    usuarioActual = loggarse()
                    println()
                }
                2 -> {
                    crearUsuario()
                    println()
                }
                3 -> {
                    mostrarProductos(productoController.listarTodosLosProductos())
                    println()
                }
                4 -> {
                    println("Introduzca el nombre del producto que desea buscar:")
                    mostrarProductos(productoController.buscarPorNombre(introducirContenido()))
                    println()
                }
                else -> exitProcess(130)
            }
            if(usuarioActual == null){
                println("Tenga en cuenta, que no ha conseguido loggarse en el sistema.")
                println()
            }
        } while (usuarioActual == null)

        when (usuarioActual!!.rol) {
            RolDeUsuario.ADMIN -> {
                println("Bienvenido, administrador $usuarioActual")
                funcionesDeAdmin()
                println()
            }
            RolDeUsuario.CLIENTE -> {
                println("Bienvenido, cliente $usuarioActual")
                funcionesDeCliente()
                println()
            }
        }
        //Una vez se desloggea el usuario, vuelvo a poner el usuario actual como indefinido (null)
        usuarioActual = null
    }
}

/**
 * función que nos permite elaborar las distintas funciones de un cliente
 */
fun funcionesDeCliente() {
    while(true){
        when(menuCliente()){
            1 -> {
                mostrarProductos(productoController.listarTodosLosProductos())
                println()
            }
            2 -> {
                println("Introduza el nombre del producto que desea buscar:")
                mostrarProductos(productoController.buscarPorNombre(introducirContenido()))
                println()
            }
            3 -> {
                try {
                    val compra = generarCompra()
                    val venta = ventaController.guardar(usuarioActual!!.id, compra)
                    pagar()
                    println("El recibo de la venta es:")
                    venta.mostrarVenta()
                }catch (e: Exception){
                    println(e.message)
                }
                println()
            }
            4 -> {
                mostrarVentas(ventaController.listarTodasLasVentasPropias(usuarioActual!!.id))
                println()
            }
            else -> {
                break
                println()
            }
        }
    }
}

fun generarCompra(): Map<Int, Int> {
    val compra = mutableMapOf<Int, Int>()
    do {
        println("Introduzca el nombre del producto que desea comprar:")
        val lista = productoController.buscarPorNombre(introducirContenido())
        if(lista.isNotEmpty()) {
            mostrarProductos(lista)
            println("Seleccione cual de esos productos quiere acquirir:")
            val productoId = conseguirIdProductoSegunLaElleccion(lista)
            println("Ahora introduczca la cantidad que desea de ese producto:")
            val cantidad = introducirNumero(0).toInt()
            if (cantidad == 0) {
                println("Se decidio no añadir ninguna cantida de producto a la cesta.")
            } else {
                compra[productoId] = cantidad
                println(
                    "Se añadio un total de $cantidad cantidad(es) del producto ${
                        productoController.buscarPorID(
                            productoId
                        )
                    }"
                )
            }
        }
    }while(quieresMasProductos())
    return compra
}

fun conseguirIdProductoSegunLaElleccion(lista: List<Producto>): Int {
    return lista.get(introducirNumero(1, lista.size).toInt()-1).id
}

/**
 * menú con las distintas funciones que puede realizar un cliente
 * @return la opción seleccionada por el usuario actual
 */
fun menuCliente(): Int {
    println("******************************************")
    println("*                  Menú                  *")
    println("******************************************")
    println("* 1. Listar todos los productos          *")
    println("* 2. Buscar producto                     *")
    println("* 3. Comprar producto(s)                 *")
    println("* 4. listar todas las ventas del cliente *")
    println("* 0. Cerrar sesión                       *")
    println("******************************************")
    return introducirNumero(0,4).toInt()
}

/**
 * función que nos permite elaborar las distintas funciones de un administrador
 */
fun funcionesDeAdmin() {
    while(true){
        when(menuAdmin()){
            1 -> {
                try {
                    mostrarProductos(productoController.listarTodosLosProductos())
                }catch (e: Exception){
                    println(e.message)
                }
                println()
            }
            2 -> {
                mostrarProductos(buscar())
                println()
            }
            3 -> {
                try {
                    println("El producto :${productoController.añadirProducto(crearProducto())}, fue añadido al almacén.")
                }catch (e: Exception){
                    println(e.message)
                }
                println()
            }
            4 -> {
                try {
                    println("Introduzca el id del producto que desea eliminar del almacen:")
                    println("El producto :${productoController.eliminarProducto(introducirNumero(1).toInt())}, fue eliminado del almacén.")
                }catch (e: Exception){
                    println(e.message)
                }
                println()
            }
            5 -> {
                mostrarVentas(ventaController.listarTodasLasVentas())
                println()
            }
            else -> break
        }
    }
}

/**
 * menú con las distintas funciones que puede realizar un administrador
 * @return la opción seleccionada por el usuario actual
 */
fun menuAdmin(): Int {
    println("************************************")
    println("*               Menú               *")
    println("************************************")
    println("* 1. Listar todos los productos    *")
    println("* 2. Buscar producto               *")
    println("* 3. Añadir producto al almacén    *")
    println("* 4. Eliminar producto del almacén *")
    println("* 5. Listar todas las ventas       *")
    println("* 0. Cerrar sesión                 *")
    println("************************************")
    return introducirNumero(0,5).toInt()
}

/**
 * función que nos permite loggarnos
 * @return el usuario como el que nos hemos loggado con los datos de username y password correctos, o null si los datos fueron incorrectos
 */
fun loggarse(): Usuario? {
    println("Seleccione como que tipo de usuario desea loggarse:")
    val rol = seleccionarRol()
    if(userController.buscarSegunRol(rol).isNotEmpty()) {
        println("(Nota: La cantidad de asteriscos no se relaciona para nada con la longitud de la contraseña)")
        println("Los clientes disponibles son:")
        userController.buscarSegunRol(rol).forEach {
            println("""username=${it.username} password="********"""")
        }
        println("Primero introduzca su username:")
        val username = introducirContenido()
        println("Ahora introduzca su password:")
        val password = introducirContenido()
        return userController.buscarSegunRol(rol)
            .find { it.username == username && it.password == password }
    }else{
        println("No hay usuarios del rol ${rol.name} creados.")
        return null
    }
}

/**
 * función que nos permite seleccionar la opción con respecto al usuario actual
 * @return la opción seleccionada
 */
fun seleccionarUsuarioActual(): Int {
    println("*********************************")
    println("*             Menú              *")
    println("*********************************")
    println("* 1. Loggarse                   *")
    println("* 2. Crear nuevo usuario        *")
    println("* 3. Listar todos los productos *")
    println("* 4. Buscar producto            *")
    println("* 0. Salir                      *")
    println("*********************************")
    return introducirNumero(0,4).toInt()
}

/**
 * función para representar la lista de productos
 */
fun mostrarProductos(list: List<Producto>) {
    println("Los productos encontrados son:")
    var contador = 1
    list.forEach {
        println(" $contador. $it")
        contador++
    }
}

/**
 * función para representar la lista de ventas del usuario actual
 */
fun mostrarVentas(list: List<Venta>) {
    println("Las ventas del usuario: $usuarioActual, son:")
    var contador = 1
    list.forEach {
        println(" $contador. $it")
        contador++
    }
}

/**
 * función que sirve para pagar la venta
 * @param venta es la venta a pagar
 * @return el recibo de la venta
 */
fun pagar(){
    println("Seleccione el método de pago que deseé:")
    when(metodoPago()){
        1 -> pagarConTarjeta()
        else -> pagarConPaypal()
    }
}

/**
 * función que "emula" pagar con Paypal
 */
fun pagarConPaypal() {
    println("Introduzca su contraseña de la cuenta de Paypal:")
    var contraseña = ""
    val regex = Regex("[A-Z]{1}[a-z]+[0-9]+")
    do {
        contraseña = readln().trim()
        if(!contraseña.matches(regex)){
            println("""La contraseña debe empezar en mayúscula, seguir con alguna letra minuscula y finalmente terminar con algún número, por ejemplo: "Hola1234", vuelva a introducirlo:""")
        }
    }while(!contraseña.matches(regex))
    println("Perfecto muchas gracias, ya se ha realizado el pago.")
}

/**
 * función que "emula" pagar con tarjeta
 */
fun pagarConTarjeta() {
    println("Introduzca su número de tarjeta:")
    var tarjeta = ""
    val regex = Regex("[0-9]{16}")
    do {
        tarjeta = readln().trim()
        if(!tarjeta.matches(regex)){
            println("El número de tarjeta no es válido, vuelva a introducirlo:")
        }
    }while(!tarjeta.matches(regex))
    println("Perfecto muchas gracias, ya se ha realizado el pago.")
}

/**
 * función que nos permite seleccionar un método de pago
 * @return la opción del método de pago seleccionada
 */
fun metodoPago(): Int {
    println("*****************************")
    println("*           Menú            *")
    println("*****************************")
    println("* 1. Pago con tarjeta       *")
    println("* 2. Pago con Paypal        *")
    println("*****************************")
    return introducirNumero(1,2).toInt()
}

/**
 * función para comprobar si el cliente quiere añadir más productos a la venta
 * @return true en caso de que quiera añadir más, false en caso contrario
 */
fun quieresMasProductos(): Boolean {
    println("Deseas añadir más productos a la cesta?(s/n)")
    var respuesta = ""
    do{
        try {
            respuesta = readln().trim().lowercase()
            respuestaValida(respuesta)
        }catch (e: Exception){
            respuesta = ""
            println(e.message)
        }
    }while(respuesta == "")
    return when(respuesta){
        "s" -> true
        else -> false
    }
}

/**
 * función que sirve para validar la respuesta
 * @param repuesta lo que queremos validar
 * @throws IllegalArgumentException si la respuesta no es valida
 * @return true si la respuesta si es válida
 */
fun respuestaValida(respuesta: String): Boolean{
    require(respuesta == "s" || respuesta == "n"){
        """No ha introducido una opción válida, "s" es para si y "n" es para no, vuelva a probar:"""
    }
    return true
}

/**
 * función que muestra el menú de tipos de busqueda, y pide una elección
 * @return el resultado de esa elección
 */
fun menuSeleccionTipoBusqueda(): Int {
    println("*****************************")
    println("*           Menú            *")
    println("*****************************")
    println("* 1. Buscar por ID          *")
    println("* 2. Buscar por nombre      *")
    println("*****************************")
    return introducirNumero(1, 2).toInt()
}

/**
 * función que lista todos los productos existentes en el almacen, según un criterio de busqueda
 * @return una lista con todos los productos, según el criterio de busqueda
 */
fun buscar(): List<Producto> {
    return when(menuSeleccionTipoBusqueda()){
        1 -> {
            println("Introduzca el id del producto a buscar:")
            listOf(productoController.buscarPorID(introducirNumero(1).toInt()))
        }
        else -> {
            println("Introduzca el nombre del producto a buscar:")
            productoController.buscarPorNombre(introducirContenido())
        }
    }
}