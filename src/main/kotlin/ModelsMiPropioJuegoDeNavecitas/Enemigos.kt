package ModelsMiPropioJuegoDeNavecitas

class Enemigos() {

    companion object {
        private var contador = 1
        fun nextId(): Int {
            return contador++
        }
        private var contadorV = 1
        fun nextVida(): Int {
            return contadorV++
        }
    }

    var id: Int = nextId()

    var vida: Int = nextVida()
}