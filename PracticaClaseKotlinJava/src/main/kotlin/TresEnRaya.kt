fun main(){
    var tablero: Array<Array<String>> = Array(3){Array<String>(3){" "} }

    jugar(tablero)
}

/**
 * función que sirve para jugar al tres en raya
 * @param tablero la matriz vacia tablero con la que jugamos
 */
fun jugar(tablero: Array<Array<String>>) {
    do{
        if(tableroDisponible(tablero)) {
            println("Por favor, introduzca las coordenadas sobre las que pondrá su X (debes respetar el formato n-n):")
            var coordenadasJugador = introducirCoordenadas(tablero)
            tablero[coordenadasJugador.first][coordenadasJugador.second] = "X"
            println()
            println("Tras tu adición el tablero se ve así:")
            representarTablero(tablero)
            Thread.sleep(2_000L)
        }
        if(Victoria(tablero)){
            break
        }
        if(tableroDisponible(tablero)) {
            var coordenadasOrdenador = introducirCoordenadasOrdenador(tablero)
            tablero[coordenadasOrdenador.first][coordenadasOrdenador.second] = "O"
            println()
            println("Tras la acción del ordenador el tablero se ve así:")
            representarTablero(tablero)
            Thread.sleep(2_000L)
        }
        if(Victoria(tablero)){
            break
        }
    }while(tableroDisponible(tablero))
    if(tableroDisponible(tablero) == false){
        println("Se han acabado las casillas, ha sido un empate")
    }
}

/**
 * función que sirve par saber si hay un ganador o no, y en caso de que lo halla, lo comenta
 * @param tablero es la matriz sobre la que calcularemos si alguién ganó o no
 * @return true si alguién ganó, false si nadie ganó
 */
fun Victoria(tablero: Array<Array<String>>): Boolean {
    for(i in tablero.indices){
        for(j in tablero[i].indices){
            if(tablero[i][j] != " ") {
                var contadorHorizontal = 1
                var contadorVertical = 1
                var contadorDiagonal = 1
                var fila = i - 1
                while (fila > 0) {
                    if (tablero[fila][j] == tablero[i][j]) {
                        contadorVertical++
                    }
                    fila--
                }
                fila = i + 1
                while (fila <= 2) {
                    if (tablero[fila][j] == tablero[i][j]) {
                        contadorVertical++
                    }
                    fila++
                }
                var columna = j - 1
                while (columna > 0) {
                    if (tablero[i][columna] == tablero[i][j]) {
                        contadorHorizontal++
                    }
                    columna--
                }
                columna = j + 1
                while (columna <= 2) {
                    if (tablero[i][columna] == tablero[i][j]) {
                        contadorHorizontal++
                    }
                    columna++
                }
                if(i == 0 && j == 0){
                    if(tablero[1][1] == tablero[i][j] && tablero[2][2] == tablero[i][j]){
                        contadorDiagonal = 3
                    }
                }
                if(i == 0 && j == 2){
                    if(tablero[1][1] == tablero[i][j] && tablero[2][0] == tablero[i][j]){
                        contadorDiagonal = 3
                    }
                }
                if (contadorVertical >= 3 || contadorHorizontal >= 3 || contadorDiagonal >= 3) {
                    println("El ganador es: ${tablero[i][j]}")
                    return true
                }
            }
        }
    }
    return false
}

/**
 * función que sirve para comprobar si aun quedán casillas disponibles para colocar piezas
 * @param tablero la matriz sobre la que comprobamos si quedán espacios libres
 * @return true si quedán huecos libres, false si no quedán
 */
fun tableroDisponible(tablero: Array<Array<String>>): Boolean {
    for(i in tablero.indices){
        for(j in tablero[i].indices){
            if(tablero[i][j] == " ") {
                return true
            }
        }
    }
    return false
}

/**
 * función que sirve para que el ordenador haga su jugada
 * @param tablero la matriz con la que comprobaremos que no se repite ninguna casilla
 * @return un par de enteros que son la fila y columna introducidas al azar
 */
fun introducirCoordenadasOrdenador(tablero: Array<Array<String>>): Pair<Int, Int> {
    var coordenadas = ""
    do {
        coordenadas = "${(1..3).random()}-${(1..3).random()}"
    }while( posicionNoElegida(tablero, coordenadas.split("-")[0].toInt() - 1, coordenadas.split("-")[1].toInt() - 1))
    return Pair(coordenadas.split("-")[0].toInt() - 1, coordenadas.split("-")[1].toInt() - 1)
}

/**
 * función que representa el estado del tablero tras cada movimiento
 * @param tablero la matriz que queremos representar
 */
fun representarTablero(tablero: Array<Array<String>>) {
    var mensaje = ""
    for(i in tablero.indices){
        for(j in tablero[i].indices){
            mensaje = "$mensaje ${tablero[i][j]}"
        }
        println(mensaje)
        mensaje = ""
    }
}

/**
 * función que comprueba si las coordenadas introducida son válidas o no
 * @param coordenadas las coordenadas que comprobamos
 */
fun coordenadasValidas(coordenadas: String) {
    val regex = Regex("[1-3]-[1-3]")
    require(coordenadas.matches(regex)){"Las coordenadas introducidas no cumplen con el patrón deseado"}
}

/**
 * función que sirve para introducir las coordenadas de la ficha del jugador
 * @param tablero la matriz con la que comprobaremos que no se repite ninguna casilla
 * @return un par de enteros que son la fila y columna introducidas por el jugador
 */
fun introducirCoordenadas(tablero: Array<Array<String>>): Pair<Int, Int> {
    var coordenadas = ""
    do {
        try {
            coordenadas = readln()
            coordenadasValidas(coordenadas)
        } catch (e: Exception) {
            println(e.message)
            coordenadas = ""
        }
        if(posicionNoElegida(tablero, coordenadas.split("-")[0].toInt() - 1, coordenadas.split("-")[1].toInt() - 1)){
            println("En esas coordenadas ya hay fichas, no puedes ponerte hay, vuelve a probar")
        }
    }while(coordenadas == "" || posicionNoElegida(tablero, coordenadas.split("-")[0].toInt() - 1, coordenadas.split("-")[1].toInt() - 1))
    return Pair(coordenadas.split("-")[0].toInt() - 1, coordenadas.split("-")[1].toInt() - 1)
}

/**
 * función que comprueba si las coordenadas elegidas entán libres o no
 * @return true si no están libre y false si lo están
 */
fun posicionNoElegida(tablero: Array<Array<String>>, fila: Int, columna: Int): Boolean {
    return if(tablero[fila][columna] != " "){
        true
    }else{
        false
    }
}
