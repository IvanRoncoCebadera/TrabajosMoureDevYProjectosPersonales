package ModelsMiPropioJuegoDeNavecitas

data class Asteroides(val marca: String = "*", var municion: Int, var suministro: Int) {

    companion object{
        private var contador = 0
        fun nextId(): Int{
            return contador++
        }
    }

    var id: Int = nextId()
}