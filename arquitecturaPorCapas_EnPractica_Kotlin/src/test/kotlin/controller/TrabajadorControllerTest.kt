package controller

import Exception.TrabajadorNoEncontrado
import Exception.TrabajadorYaExistente
import models.Trabajador
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import repositories.TrabajadorRepository
import repositories.TrabajadorRepositoryMemory
import java.util.*

internal class TrabajadorControllerTest {

    private val trabajadoresIniciales: Array<Trabajador?> = arrayOf(
        Trabajador.Chapista(
            UUID.fromString("851477e0-fe6b-46df-8eb5-278bf6088213"),
            "Iván",
            12
        ),
        Trabajador.Electricista(
            UUID.fromString("5e9462d8-aed0-4dab-b566-9008c4e6f582"),
            "Rocio",
            48
        ),
        Trabajador.ChapistaElectricista(
            UUID.fromString("54eb5e2d-7447-4172-b3d2-869ccca33b68"),
            "Radagon",
            23,
            76
        )
    )

    private lateinit var repository: TrabajadorRepository
    private lateinit var controller: TrabajadorController

    @BeforeEach
    fun setUp(){
        repository = TrabajadorRepositoryMemory()
        repository.safeAll(trabajadoresIniciales)
        controller = TrabajadorController(repository)
    }

    @Test
    fun findByIdNotFound() {
        val myId = UUID.fromString("658662d8-aed0-4dab-b566-9786c4e6f582")
        val res = org.junit.jupiter.api.assertThrows<TrabajadorNoEncontrado> {
            controller.findById(myId)
        }
        assertEquals("No se ha encontrado a ningún trabajador con ese id.", res.message)
    }

    @Test
    fun createAlreadyExistingWorker() {
        val trabajador = trabajadoresIniciales[0] as Trabajador
        val res = org.junit.jupiter.api.assertThrows<TrabajadorYaExistente> {
            controller.create(trabajador)
        }
        assertEquals("El trabajador que se trato de añadir, ya existe.", res.message)
    }

    @Test
    fun updateNotFound() {
        val trabajador = trabajadoresIniciales[0] as Trabajador
        val myId = UUID.fromString("658662d8-aed0-4dab-b566-9786c4e6f582")
        val res = org.junit.jupiter.api.assertThrows<TrabajadorNoEncontrado> {
            controller.update(myId, trabajador)
        }
        assertEquals("No se ha encontrado a ningún trabajador con ese id.", res.message)
    }

    @Test
    fun delete() {
        val myId = UUID.fromString("658662d8-aed0-4dab-b566-9786c4e6f582")
        val res = org.junit.jupiter.api.assertThrows<TrabajadorNoEncontrado> {
            controller.delete(myId)
        }
        assertEquals("No se ha encontrado a ningún trabajador con ese id.", res.message)
    }

    @Test
    fun findByEspecialidadNoWorkersWereFound() {
        repository.deleteAll()
        val res = org.junit.jupiter.api.assertThrows<TrabajadorNoEncontrado> {
            controller.findByEspecialidad(Especialidad.ELECTRICISTA)
        }
        assertEquals( "No se ha encontrado ningún trabajador de esa especialidad.", res.message)
    }
}