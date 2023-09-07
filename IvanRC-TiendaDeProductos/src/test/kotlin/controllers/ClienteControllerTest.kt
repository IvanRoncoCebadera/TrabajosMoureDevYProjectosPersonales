package controllers

import exceptions.ProductoEmptyListException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import models.Producto
import models.Venta
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import repositories.VentaRepository
import java.time.LocalDate
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ClienteControllerTest {

    val misProductos = mutableListOf<Producto>(
        Producto(id = 1, nombre = "Paella", precioUnitario = 2.50, stock = 9999),
        Producto(id = 2, nombre = "Coche", precioUnitario = 21058.42, stock = 9),
        Producto(id = 3, nombre = "Portatil", precioUnitario = 752.0, stock = 350),
        Producto(id = 4, nombre = "Raton", precioUnitario = 68.20, stock = 599),
        Producto(id = 5, nombre = "Resident Evil 4", precioUnitario = 69.99, stock = 9999),
        Producto(id = 6, nombre = "Microsoft flight simulator", precioUnitario = 58.98, stock = 2397),
    )
    val idUser = 2
    val misVentas = mutableListOf<Venta>(
        Venta(UUID.fromString("c9f03e43-dfd8-42ca-8000-8f2dd48b7f2b"), LocalDate.now(), 2)
    )

    @MockK
    private lateinit var repository: VentaRepository<Producto, Venta, Recibo, Int>
    @InjectMockKs
    private lateinit var controller: VentaController

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun listarTodosLosProductos() {
        every { repository.listarTodosLosProductos() } returns misProductos
        val productos = controller.listarTodosLosProductos()
        assertAll(
            { assertEquals(6, productos.size) },
            { assertEquals(productos.find { it.id == 1 }, misProductos.find { it.id == 1 }) },
            { assertEquals(productos.find { it.id == 2 }, misProductos.find { it.id == 2 }) },
            { assertEquals(productos.find { it.id == 3 }, misProductos.find { it.id == 3 }) },
            { assertEquals(productos.find { it.id == 4 }, misProductos.find { it.id == 4 }) },
            { assertEquals(productos.find { it.id == 5 }, misProductos.find { it.id == 5 }) },
            { assertEquals(productos.find { it.id == 6 }, misProductos.find { it.id == 6 }) }
        )
    }

    @Test
    fun listarTodasLasVentasPropias() {
        every { repository.listarTodasLasVentasPropias(idUser) } returns misVentas
        val ventas = controller.listarTodasLasVentasPropias(idUser)
        assertAll(
            { assertEquals(1, ventas.size) },
            { assertEquals(misVentas.first(), ventas.first()) }
        )
    }

    @Test
    fun comprobarHayProductos() {
        every { repository.listarTodosLosProductos() } returns misProductos
        assertTrue(controller.comprobarHayProductos())
    }

    @Test
    fun comprobarNoHayProductos(){
        every { repository.listarTodosLosProductos() } returns listOf()
        val message = assertThrows<ProductoEmptyListException> { controller.comprobarHayProductos() }
        assertEquals("Error al listar productos: No hay productos en el almacén, vuelva a intentar cuando se actualize la información de nuestro almacén.", message.message)
    }

    @Test
    fun comprobarHayVentas() {
        every { repository.listarTodasLasVentasPropias(idUser) } returns misVentas
        assertTrue(controller.comprobarHayVentas(idUser))
    }

    @Test
    fun comprobarNoHayVentas(){
        every { repository.listarTodasLasVentasPropias(idUser) } returns listOf()
        val message = assertThrows<ProductoEmptyListException> { controller.comprobarHayVentas(idUser) }
        assertEquals("Error al listar ventas: No hay ventas almacenadas de el usuario con id: $idUser.", message.message)
    }
}