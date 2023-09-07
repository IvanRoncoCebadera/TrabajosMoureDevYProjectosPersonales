import models.Persona
import models.SingletonData
import utils.UtilsJava

fun main(args: Array<String>) {
    println("Lo de Java en Kotlin:")

    println()

    val persona: Persona = Persona(
        "Manuel",
        12,
        Persona.Estudios.DAM,
        "Leganes"
    )

    println(persona)

    UtilsJava.imprimirSaludoJava()

    SingletonData.getInstance().datos = "442"

    SingletonData.getInstance().imprimirDatos()
}