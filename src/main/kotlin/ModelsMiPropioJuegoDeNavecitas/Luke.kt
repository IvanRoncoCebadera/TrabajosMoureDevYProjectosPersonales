package ModelsMiPropioJuegoDeNavecitas1

import ModelsMiPropioJuegoDeNavecitas.Asteroides

data class Luke(val marca: String = "0", var municion: Int, var combustible: Int, var vida: Int) {

    companion object {
        private var contador = 0
        fun nextId(): Int {
            return contador++
        }
    }

    var id: Int = nextId()

    /**
     * funcion que permite escribir los datos de direccion y desplazamiento
     * @return los datos de direccion y desplazamiento si son válidos
     */
    fun introducirDireccionDesplazamiento(): String{
        var datos = ""
        do{
            try{
                datos = readln().trim()
                datosValidos(datos)
            }catch(e: Exception){
                println(e.message)
                datos == ""
            }
        }while(datos == "")
        return datos
    }

    /**
     * función que comprueba si los datos son válidos
     * @param datos los datos que debemos comprobar
     */
    fun datosValidos(datos: String) {
        val regex = Regex("(derecha|izquierda|arriba|abajo);[1-9]")
        require(datos.matches(regex)){"No has metido datos válidos, vuelve a probar:"}
    }

    /**
     * funcion que encuentra a luke y devuelve la posicion sobre la que está
     * @param espacio es la matriz sobre la que se encontrara toda la info
     * @return un mensaje con las coordenadas del luke
     */
    fun encontrarLuke(espacio: Array<Array<Any?>>): String {
        var mensaje = ""
        for(i in espacio.indices){
            for(j in espacio[i].indices){
               if(espacio[i][j] == this){
                   mensaje = "$i-$j"
               }
            }
        }
        return mensaje
    }
}