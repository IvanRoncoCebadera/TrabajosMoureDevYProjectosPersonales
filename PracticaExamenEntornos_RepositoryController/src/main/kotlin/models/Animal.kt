package models

interface IPerro{
    fun ladrar()
    fun sacarAPaseo()
}

interface IGato{
    fun maullar()
    fun arañar()
}

sealed class Animal(val nombre: String, ) {
    val id: Int = nextId()
    private companion object{
        var contador = 1
        fun nextId(): Int{
            return contador++
        }
    }

    class Perro(nombre: String): Animal(nombre), IPerro{
        /**
         * El perro ladra
         */
        override fun ladrar() {
            println("Soy el perro $nombre, y te ladro.")
        }

        /**
         * el perro sale a pasear
         */
        override fun sacarAPaseo() {
            println("Soy el perro $nombre, y estoy muy contento porque voy de paseo.")
        }

        override fun toString(): String {
            return "Perro(id=$id, nombre=$nombre)"
        }
    }

    class Gato(nombre: String): Animal(nombre), IGato{
        /**
         * el gato maulla
         */
        override fun maullar() {
            println("Soy el gato $nombre, *miau*.")
        }

        /**
         * el gato araña
         */
        override fun arañar() {
            println("Soy el gato $nombre, y estoy arañando las cosas de la casa, XD.")
        }

        override fun toString(): String {
            return "Gato(id=$id, nombre=$nombre)"
        }
    }

    class Lobo(nombre: String): Animal(nombre), IPerro{
        /**
         * El lobo ladra
         */
        override fun ladrar() {
            println("Soy el lobo $nombre, y te ladro.")
        }

        /**
         * el lobo sale a pasear
         */
        override fun sacarAPaseo() {
            println("Soy el lobo $nombre, y estoy muy contento porque voy de paseo.")
        }

        override fun toString(): String {
            return "Perro(id=$id, nombre=$nombre)"
        }
    }
}