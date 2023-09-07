package repositories

import Especialidad
import models.Trabajador
import java.util.*

interface TrabajadorRepository {
    fun getAll(): Array<Trabajador>

    fun findById(id: UUID): Trabajador?

    //En caso de que el trabajador ya exita devuelve un nulo
    fun create(trabajador: Trabajador): Trabajador?

    //En caso de que no hya nada para actualizar devolver un nulo
    fun update(id: UUID, trabajador: Trabajador): Trabajador?

    //En caso de no encontrar al trabajador a eliminar devolvera un nulo
    fun delete(id: UUID): Trabajador?

    //Segun el tipo de especialidad que no introduzca, devolveremos un tipo u otro e array, pero que sera de trabajdor en general
    fun findByEspecialidad(especialidad: Especialidad): Array<Trabajador>

    fun deleteAll()

    fun safeAll(trabajadores: Array<Trabajador?>)
}