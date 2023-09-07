package models

import enums.RolDeUsuario
import exceptions.UsuarioBadRequestException

data class Usuario(val id: Int = 1, val username: String, val password: String, val rol: RolDeUsuario) {
    override fun toString(): String {
        return "Usuario(id=$id, username='$username', password='********', rol=$rol)"
    }
}