fun main(){
    var maximoTiros  = 5
    var probabilidadMaxima = 2
    var puntosLlull = generarPuntosPorRonda(probabilidadMaxima, maximoTiros)
    probabilidadMaxima = 3
    var puntosGasol = generarPuntosPorRonda(probabilidadMaxima, maximoTiros)
    var resultadoRonda = generarResultadoRonda()
}
fun generarPuntosPorRonda(probabilidadMaxima: Int, maximoTiros: Int): Int{
    var resultado = 0
    var probabilidad = (1.. probabilidadMaxima).random()
    var contador = 0
    do{
    if(contador < maximoTiros){
        if(probabilidad == 1){
            println("Ha metido su tiro número $contador. Más 1 punto.")
            resultado = resultado + 1
        }
    }else {
        if (probabilidad == 1) {
            println("Ha metido su tiro número $contador. Más 2 puntos.")
            resultado = resultado + 2
        }
    }
    }while(contador < maximoTiros)
}