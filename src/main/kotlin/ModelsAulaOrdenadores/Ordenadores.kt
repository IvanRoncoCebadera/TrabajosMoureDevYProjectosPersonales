package ModelsAulaOrdenadores

class Ordenadores {

    val id = nextId()

    companion object{
        var contador = 1
        fun nextId(): Int{
            return contador++
        }
    }

    override fun toString(): String {
        return "Ordenador(id=$id)"
    }


}