package models

data class Perro(var nombre: String, var edad: Int = (1..38).random(), var raza: Raza, var accidente: String) {

    enum class Raza {
        CANICHE, BULLDOG
    }
}
