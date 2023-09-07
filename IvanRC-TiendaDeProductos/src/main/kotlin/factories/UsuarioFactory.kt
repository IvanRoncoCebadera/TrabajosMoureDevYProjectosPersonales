package factories

import enums.RolDeUsuario
import exceptions.UsuarioBadRequestException
import general.General
import models.Usuario
import repositories.UsuarioRepositoryImpl

object UsuarioFactory {
    val usuarios = UsuarioRepositoryImpl

    /**
     * función que nos permite crear un usuario sin que se repita su username o password
     * @return cl
     */
    fun crearUsuario(): Usuario {
        var user: Usuario? = null
        println("Seleccione el rol que desea que tenga el nuevo usuario:")
        var rol: RolDeUsuario = seleccionarRol()

        do {
            println("Ahora introduzca el username del usuario:")
            var username = ""
            var estaRepetido = false
            do {
                username = readln().trim()
                estaRepetido = estaElUsernameRepetido(username)
                if (estaRepetido) {
                    println("No se puede seleccionar un username que ya existe, vuelve a probar:")
                }
            } while (estaRepetido)

            println("Ahora introduzca el password del usuario:")
            var password = ""
            do {
                password = readln().trim()
                estaRepetido = estaElPasswordRepetido(password)
                if (estaRepetido) {
                    println("No se puede seleccionar un password que ya existe, vuelve a probar:")
                }
            } while (estaRepetido)

            try {
                user = Usuario(username = username, password = password, rol = rol)
            } catch (e: UsuarioBadRequestException) {
                println(e.message)
                user = null
            }
        }while (user == null)

        usuarios.añadir(user)

        return user
    }

    /**
     * función que valida si se repite o no el username
     * @return true si se repite, y false en caso contrario
     */
    fun estaElUsernameRepetido(username: String): Boolean {
        if(usuarios.buscarPorNombre(username).isEmpty()){
             return false
        }
        return true
    }

    /**
     * función que valida si se repite o no el password
     * @return true si se repite, y false en caso contrario
     */
    fun estaElPasswordRepetido(password: String): Boolean {
        if(usuarios.listarTodo().isNotEmpty()){
            if(usuarios.listarTodo().find{ it.password == password } != null) return true
        }
        return false
    }

    /**
     * función que muestra el menú de roles, y pide una elección
     * @return el rol resultado de esa elección
     */
    fun seleccionarRol(): RolDeUsuario {
        println("*********************************")
        println("*             Menú              *")
        println("*********************************")
        println("* 1. Loggarse como admin        *")
        println("* 2. Loggarse como usuario      *")
        println("*********************************")
        return when(General.introducirNumero(1, 2).toInt()){
            1 -> RolDeUsuario.ADMIN
            else -> RolDeUsuario.CLIENTE
        }
    }
}