package EjerciciosTest

import ordenarBurbujaAscendente
import org.junit.jupiter.api.Test
import kotlin.test.DefaultAsserter.assertEquals

class EjercicioTest {

    @Test
    fun ordenarBurbujaAscendenteTest(): Unit {
        assertEquals("Hola tu que ase", intArrayOf(1,2,3,4,5), ordenarBurbujaAscendente(intArrayOf(2,4,1,5,3)))
    }

}