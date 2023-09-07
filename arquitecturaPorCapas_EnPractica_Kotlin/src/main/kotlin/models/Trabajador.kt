package models

import java.util.*

interface IChapista{
    var nChapas: Int
    fun chapar()
}

interface IElectricista{
    var nCables: Int
    fun pelarCable()
}

sealed class Trabajador(
    val id: UUID = UUID.randomUUID(),
    val nombre: String
) {
    open class Chapista(
        id: UUID,
        nombre: String,
        override var nChapas: Int
    ): Trabajador(id, nombre), IChapista{
        override fun chapar() {
            println("Soy el chapista $nombre, con el id: $id, y estoy chapando un total de $nChapas chapas...")
        }
    }

    class Electricista(
        id: UUID,
        nombre: String,
        override var nCables: Int
        ): Trabajador(id, nombre), IElectricista{
        override fun pelarCable() {
            println("Soy el electricista $nombre, con el id: $id, y voy a pelar un total de $nCables cables...")
        }
    }

    class ChapistaElectricista(
        id: UUID,
        nombre: String,
        nChapas: Int,
        override var nCables: Int
    ): Chapista(id, nombre, nChapas), IElectricista{
        override fun pelarCable() {
            println("Soy el ChapistaElectricista $nombre, con el id: $id, y voy a pelar un total de $nCables cables...")
        }

        override fun chapar() {
            println("Soy el ChapistaElectricista $nombre, con el id: $id, y estoy chapando un total de $nChapas chapas...")
        }
    }

}