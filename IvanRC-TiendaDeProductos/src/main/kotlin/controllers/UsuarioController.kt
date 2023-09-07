package controllers

import enums.RolDeUsuario
import exceptions.UsuarioNotFoundException
import models.Usuario
import repositories.UsuarioRepository
import validator.validar

class UsuarioController(
    private val repository: UsuarioRepository
) {
    /**
     * función que consigue un listado con todos los usuarios del sistema segun el nombre especificado
     * @return el listado conseguido
     */
    fun buscarPorNombre(nombre: String): List<Usuario> {
        return repository.buscarPorNombre(nombre)
    }

    /**
     * función que consigue un listado con todos los usuarios del sistema segun el rol especificado
     * @return el listado conseguido
     */
    fun buscarSegunRol(rol: RolDeUsuario): List<Usuario> {
        return repository.buscarSegunRol(rol)
    }

    /**
     * función que consigue un listado con todos los usuarios del sistema
     * @return el listado conseguido
     */
    fun listarTodo(): List<Usuario> {
        return repository.listarTodo()
    }

    /**
     * función que buscar al usuario del id especificado y si existe lo elimina
     * @param id el id del user a buscar
     * @return null si no hay usuario con ese id, y en caso contrario, al user eliminado
     * @throws UsuarioNotFoundException si no encuentra el usuario a eliminar
     */
    fun eliminar(id: Int): Usuario {
        return repository.eliminar(id) ?: throw UsuarioNotFoundException("Fallo al buscar al usuario: No se encontro al usuario a quien se queria eliminar.")
    }

    /**
     * función que sirve para añadir/actualizar un ususario
     * @param entity el usuario a añadir/actualizar
     * @return el ususario cuyos datos hemos almacenado
     */
    fun añadir(entity: Usuario): Usuario {
        entity.validar(buscarSegunRol(entity.rol))
        return repository.añadir(entity)!!
    }

    fun actualizar(entity: Usuario): Usuario{
        entity.validar(buscarSegunRol(entity.rol))
        return repository.actualizar(entity) ?: throw UsuarioNotFoundException("Fallo al buscar al usuario: No se encontro al usuario a quien se queria actualizar.")
    }
}