package general

import models.Producto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GeneralTest {

    @Test
    fun validarNumero(){
        assertTrue(General.validarNumero("1",0,1))
    }

    @Test
    fun numeroNoValido(){
        val message1 = assertThrows<IllegalArgumentException> { General.validarNumero("", 1, 2) }
        val message2 = assertThrows<IllegalArgumentException> { General.validarNumero("vgfwegw", 1, 2) }
        val message3 = assertThrows<IllegalArgumentException> { General.validarNumero("0", 1, 2) }
        assertAll(
            { assertEquals("Error al introducir datos: El dato del usuario no puede estar vacio, vuelva aprobar:", message1.message)},
            { assertEquals("Error al introducir datos: El dato del usuario debe ser un número positivo, vuelva aprobar:", message2.message)},
            { assertEquals("Error al introducir datos: El dato del usuario debe ser un número entre los valores 1 y 2, vuelva aprobar:", message3.message)}
        )
    }

    @Test
    fun validarContenido(){
        assertTrue(General.validarContenido("Nombre"))
    }

    @Test
    fun contenidoNoValido(){
        val message = assertThrows<IllegalArgumentException> { General.validarContenido("") }
        assertEquals("Error al introducir el contenido: EL contenido no puede estar vacio, vuelva a probar:", message.message)
    }

    @Test
    fun validarNumeroDecimal(){
        assertTrue(General.validarNumeroDecimal("1.0",0.0))
    }

    @Test
    fun numeroDecimalNoValido(){
        val message1 = assertThrows<IllegalArgumentException> { General.validarNumeroDecimal("", 1.0) }
        val message2 = assertThrows<IllegalArgumentException> { General.validarNumeroDecimal("vgfwegw", 1.0) }
        val message3 = assertThrows<IllegalArgumentException> { General.validarNumeroDecimal("0.5", 1.0) }
        assertAll(
            { assertEquals("Error al introducir datos: El dato del usuario no puede estar vacio, vuelva aprobar:", message1.message)},
            { assertEquals("Error al introducir datos: El dato del usuario debe ser un número decimal positivo, vuelva aprobar:", message2.message)},
            { assertEquals("Error al introducir datos: El dato del usuario debe ser un número decimal que como mínimo valga 1.0, vuelva aprobar:", message3.message)}
        )
    }

    val misProductos = mutableListOf<Producto>(
        Producto(id = 1, nombre = "Paella", precioUnitario = 2.50, stock = 9999),
        Producto(id = 2, nombre = "Coche", precioUnitario = 21058.42, stock = 9),
        Producto(id = 3, nombre = "Portatil", precioUnitario = 752.0, stock = 350),
        Producto(id = 4, nombre = "Raton", precioUnitario = 68.20, stock = 599),
        Producto(id = 5, nombre = "Resident Evil 4", precioUnitario = 69.99, stock = 9999),
        Producto(id = 6, nombre = "Microsoft flight simulator", precioUnitario = 58.98, stock = 2397),
    )

    @Test
    fun listarTodosLosProductos() {
        val productos = General.listarTodosLosProductos()
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
    fun buscarSegunId(){
        assertEquals(General.buscarSegunId(1).first(), misProductos.find { it.id == 1 })
    }

    @Test
    fun buscarSegunNombre(){
        assertEquals(General.buscarSegunNombre("res").first(), misProductos.find { it.nombre == "Resident Evil 4" })
    }
}