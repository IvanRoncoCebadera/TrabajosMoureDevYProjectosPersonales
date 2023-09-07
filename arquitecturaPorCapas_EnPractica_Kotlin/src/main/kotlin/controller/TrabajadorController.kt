package controller

import Especialidad
import Exception.TrabajadorNoEncontrado
import Exception.TrabajadorYaExistente
import models.Trabajador
import repositories.TrabajadorRepository
import java.util.*

class TrabajadorController(
    val repository: TrabajadorRepository
) {

    fun getAll() = repository.getAll()

    fun findById(id: UUID): Trabajador = repository.findById(id) ?: throw TrabajadorNoEncontrado("No se ha encontrado a ningún trabajador con ese id.")

    fun create(trabajador: Trabajador): Trabajador = repository.create(trabajador) ?: throw TrabajadorYaExistente("El trabajador que se trato de añadir, ya existe.")

    fun update(id: UUID, trabajador: Trabajador): Trabajador = repository.update(id, trabajador) ?: throw TrabajadorNoEncontrado("No se ha encontrado a ningún trabajador con ese id.")

    fun delete(id: UUID): Trabajador = repository.delete(id) ?: throw TrabajadorNoEncontrado("No se ha encontrado a ningún trabajador con ese id.")

    fun findByEspecialidad(especialidad: Especialidad): Array<Trabajador>{
        val array = repository.findByEspecialidad(especialidad)
        if(array.size == 0){
            throw TrabajadorNoEncontrado("No se ha encontrado ningún trabajador de esa especialidad.")
        }
        return array
    }

    fun deleteAll() = repository.deleteAll()

    fun safeAll(trabajadores: Array<Trabajador?>) = repository.safeAll(trabajadores)

}