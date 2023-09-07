package repositories

import models.Trabajador
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertAll
import java.util.*

internal class PersonalRepositoryMemoryTest {

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

    @BeforeEach
    fun setUp(){
        repository = TrabajadorRepositoryMemory()
        repository.safeAll(trabajadoresIniciales)
    }

    @Test
    fun getAll() {
        val trabajadores = repository.getAll()
        assertAll(
            {assertEquals(trabajadores.size, trabajadoresIniciales.size)},
            {assertEquals(trabajadores[0], trabajadoresIniciales[0])},
            {assertEquals(trabajadores[1], trabajadoresIniciales[1])},
            {assertEquals(trabajadores[2], trabajadoresIniciales[2])}
        )
    }

    @Test
    fun findById() {
        assertEquals(trabajadoresIniciales[1] , repository.findById(UUID.fromString("5e9462d8-aed0-4dab-b566-9008c4e6f582")))
    }

    @Test
    fun findByIdNoValido() {
        assertEquals(null, repository.findById(UUID.fromString("658662d8-aed0-4dab-b566-9786c4e6f582")))
        //Ahora comprobamos que pasa cuando el array está vacio
        repository.deleteAll()
        assertEquals(null, repository.findById(UUID.fromString("5e9462d8-aed0-4dab-b566-9008c4e6f582")))
    }

    @Test
    fun create() {
        val nuevoTrabajador = repository.create(
            Trabajador.Chapista(
                UUID.fromString("9452a1a7-3ae2-48a8-a563-1f039ac6aac6"),
                "Jorge",
                4)

        )
        val trabajadores = repository.getAll()
        assertAll(
            {assertEquals(4, trabajadores.size)},
            {assertEquals( trabajadores[3], nuevoTrabajador)}
        )
    }

    @Test
    fun createNoValido() {
        val nuevoTrabajador = repository.create(trabajadoresIniciales[0] as Trabajador)
        val trabajadores = repository.getAll()
        assertAll(
            {assertEquals(3, trabajadores.size)},
            {assertEquals( null, nuevoTrabajador)}
        )
    }

    @Test
    fun update() {
        val nuevoTrabajador = Trabajador.Chapista(
                UUID.fromString("9452a1a7-3ae2-48a8-a563-1f039ac6aac6"),
                "Jorge",
                4
        )
        repository.update(UUID.fromString("851477e0-fe6b-46df-8eb5-278bf6088213"), nuevoTrabajador)
        val trabajadores = repository.getAll()
        assertEquals(nuevoTrabajador, trabajadores[0])
    }

    @Test
    fun updateNoValido() {
        assertEquals(null, repository.update(UUID.fromString("658662d8-aed0-4dab-b566-9786c4e6f582"), trabajadoresIniciales[0] as Trabajador))
        repository.deleteAll()
        assertEquals(null, repository.update(UUID.fromString("5e9462d8-aed0-4dab-b566-9008c4e6f582"), trabajadoresIniciales[0] as Trabajador))
    }

    @Test
    fun delete() {
        repository.delete(UUID.fromString("54eb5e2d-7447-4172-b3d2-869ccca33b68"))
        val trabajadores = repository.getAll()
        assertEquals(2, trabajadores.size)
    }

    @Test
    fun deleteNoValido() {
        assertEquals(null, repository.delete(UUID.fromString("658662d8-aed0-4dab-b566-9786c4e6f582")))
        repository.deleteAll()
        assertEquals(null, repository.delete(UUID.fromString("5e9462d8-aed0-4dab-b566-9008c4e6f582")))
    }

    @Test
    fun deleteAll() {
        repository.deleteAll()
        val trabajadores = repository.getAll()
        assertEquals(0, trabajadores.size)
    }

    @Test
    fun safeAll() {
        val trabajadores = repository.getAll()
        assertAll(
            {assertEquals(trabajadores.size, trabajadoresIniciales.size)},
            {assertEquals(trabajadores[0], trabajadoresIniciales[0])},
            {assertEquals(trabajadores[1], trabajadoresIniciales[1])},
            {assertEquals(trabajadores[2], trabajadoresIniciales[2])}
        )
    }

    @Test
    fun encontrarChapistasTest(){
        val t1 = arrayOf(trabajadoresIniciales[0], trabajadoresIniciales[2])
        val t2 = repository.findByEspecialidad(Especialidad.CHAPISTA)
        assertAll(
            {assertEquals(t1[0], t2[0])},
            {assertEquals(t1[1], t2[1])}
        )
    }

    @Test
    fun encontrarElectricistasTest(){
        val t1 = arrayOf(trabajadoresIniciales[1], trabajadoresIniciales[2])
        val t2 = repository.findByEspecialidad(Especialidad.ELECTRICISTA)
        assertAll(
            {assertEquals(t1[0], t2[0])},
            {assertEquals(t1[1], t2[1])}
        )
    }

    @Test
    fun encontrarChapistasElectricistasTest(){
        val t1 = arrayOf(trabajadoresIniciales[2])
        val t2 = repository.findByEspecialidad(Especialidad.CHAPISTA_ELECTRICISTA)
        assertEquals(t1[0], t2[0])
    }
}