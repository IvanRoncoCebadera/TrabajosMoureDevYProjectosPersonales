package repositories

import enums.RolDeUsuario
import models.Usuario

interface UsuarioRepository: CrudRepository<Usuario, Int> {
    fun buscarPorNombre(nombre: String): List<Usuario>
    fun buscarSegunRol(rol: RolDeUsuario): List<Usuario>
}