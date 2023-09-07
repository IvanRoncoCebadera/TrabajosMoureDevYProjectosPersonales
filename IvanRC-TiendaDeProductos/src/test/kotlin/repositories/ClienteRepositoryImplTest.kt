package repositories

import models.Venta
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ClienteRepositoryImplTest {

    val repository = VentaRepositoryImpl()
    val idUser = 2
    val misVentas = mutableListOf<Venta>(
        Venta(UUID.fromString("c9f03e43-dfd8-42ca-8000-8f2dd48b7f2b"), LocalDate.now(), 2)
    )


    @Test
    fun listarTodasLasVentasPropias() {
        val ventas = repository.listarTodasLasVentasPropias(idUser)
        assertAll(
            { assertEquals(1, ventas.size) },
            { assertEquals(ventas.first(), misVentas.first()) }
        )
    }

    @Test
    fun respuestaValida() {
        assertTrue(repository.respuestaValida("s"))
    }

    @Test
    fun respuestaNoValida(){
        val message = assertThrows<IllegalArgumentException> { repository.respuestaValida("") }
        assertEquals("""No ha introducido una opción válida, "s" es para si y "n" es para no, vuelva a probar:""", message.message)
    }
}