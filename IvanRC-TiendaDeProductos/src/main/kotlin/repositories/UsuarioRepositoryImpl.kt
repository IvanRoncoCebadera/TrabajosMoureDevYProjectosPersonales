package repositories

import enums.RolDeUsuario
import models.Usuario

object UsuarioRepositoryImpl: UsuarioRepository {
    private val usuarios = mutableListOf<Usuario>(
        Usuario(1, "Ivan", "Ivan", RolDeUsuario.ADMIN),
        Usuario(1, "Ivan", "Ivan", RolDeUsuario.CLIENTE)
    )

    /**
     * función que consigue un listado con todos los usuarios del sistema segun el nombre especificado
     * @return el listado conseguido
     */
    override fun buscarPorNombre(nombre: String): List<Usuario> {
        return usuarios.filter { it.username.lowercase().contains(nombre.lowercase()) }
    }

    /**
     * función que consigue un listado con todos los usuarios del sistema segun el rol especificado
     * @return el listado conseguido
     */
    override fun buscarSegunRol(rol: RolDeUsuario): List<Usuario> {
        return usuarios.filter { it.rol == rol }
    }

    /**
     * función que consigue un listado con todos los usuarios del sistema
     * @return el listado conseguido
     */
    override fun listarTodo(): List<Usuario> {
        return usuarios.toList()
    }

    /**
     * función que buscar al usuario del id especificado y si existe lo elimina
     * @param id el id del user a buscar
     * @return null si no hay usuario con ese id, y en caso contrario, al user eliminado
     */
    override fun eliminar(id: Int): Usuario? {
        (usuarios.find { it.id == id })?.let{
            usuarios.remove(it)
            return it
        }
        return null
    }

    /**
     * función que sirve para añadir un ususario
     * @param entity el usuario a añadir
     * @return el ususario cuyos datos hemos almacenado
     */
    override fun añadir(entity: Usuario): Usuario {
        usuarios.add(entity)
        return entity
    }

    /**
     * función que sirve para actualizar un ususario
     * @param entity el usuario a actualizado
     * @return el ususario cuyos datos hemos almacenado o nulo
     */
    override fun actualizar(entity: Usuario): Usuario? {
        (buscarPorNombre(entity.username))?.let{
            usuarios.remove(it.first())
            usuarios.add(entity)
            return entity
        }
        return null
    }
}