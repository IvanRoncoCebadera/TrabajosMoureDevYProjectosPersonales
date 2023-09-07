package com.example.saludaryguardar_kotlin.repository.alumno

import com.example.saludaryguardar_kotlin.models.Alumno
import mu.KotlinLogging

private val logger = KotlinLogging.logger {  }

class AlumnoRepositoyImpl: AlumnoRepositoryBase {

    private val alumnos = mutableMapOf<String, Alumno>()

    override fun getAll(): List<Alumno> {
        logger.debug { "Consigo todos los alumnos" }
        return alumnos.values.toList()
    }

    override fun getById(id: String): Alumno? {
        logger.debug { "Consigo el alumno del email: "+id }
        return alumnos.getOrDefault(id, null)
    }

    override fun save(entity: Alumno): Alumno {
        logger.debug { "Guardo/Actualizo un alumno" }
        alumnos[entity.email] = entity
        return entity
    }

    override fun delete(id: String): Boolean {
        logger.debug { "Elimino a un alumno" }
        val size = alumnos.size
        alumnos.remove(id)
        return size < alumnos.size
    }
}