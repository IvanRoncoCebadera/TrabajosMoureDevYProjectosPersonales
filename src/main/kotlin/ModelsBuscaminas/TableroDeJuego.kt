package ModelsBuscaminas

class TableroDeJuego {

    val TAMAÑO_TABLERO = 8

    var banderasRestantes = 15

    var tablero = Array(TAMAÑO_TABLERO){Array<Minas>(TAMAÑO_TABLERO){Minas()}}

    fun inicializarTableroDeJuego(){
        var contador = 0
        do{
            var fila = (0..TAMAÑO_TABLERO-1).random()
            var columna = (0..TAMAÑO_TABLERO-1).random()
            if(tablero[fila][columna].esUnaMina == false){
                tablero[fila][columna].esUnaMina = true
                contador++
                for(i in -1..1){
                    for(j in -1..1){
                        if(fila + i >= 0 && fila + i < TAMAÑO_TABLERO && columna + j >= 0 && columna + j < TAMAÑO_TABLERO){
                            tablero[fila+i][columna+j].numeroDeMinasAdyacentes++
                        }
                    }
                }
            }
        }while(contador < 8)
    }

    fun simuladorDeJuego(){
        inicializarTableroDeJuego()
        println("Por ahora, el tablero se ve así:")
        representarMatriz()
        println()

        do{
            println("Seleccione la opción que desea:")
            when(menu()){
                1 -> {
                    println("Introduzca las coordenadas de la casilla a destapar:")
                    val coordenadas = introducirPosicion()
                    destaparCasillas(coordenadas)
                }
                2 ->{
                    println("Introduzca las coordenadas de la casilla a marcar:")
                    val coordenadas = introducirPosicion()
                    val fila = coordenadas.first
                    val columna = coordenadas.second
                    tablero[fila][columna].estaMarcadaCoUnBandera = true
                    banderasRestantes--
                }
            }
            println()
            representarMatriz()
            println()
            Thread.sleep(1000)
        }while(ningunaMinaHaEstallado() && siguenMinasOcultas())
        if(ningunaMinaHaEstallado() == false){
            println("Destapaste una mina, por lo que has muerto.")
        }else{
            if(siguenMinasOcultas() == false){
                println("Felicidades, has destapado todas las mina, por lo que has ganado.")
            }
        }
    }

    fun destaparCasillas(coordenadas: Pair<Int, Int>) {
        var fila = coordenadas.first
        var columna = coordenadas.second
        tablero[fila][columna].estaDestapada = true
        if(tablero[fila][columna].esUnaMina == false && tablero[fila][columna].numeroDeMinasAdyacentes == 0) {
            for (i in -2..2) {
                for (j in -2..2) {
                    if (fila + i >= 0 && fila + i < TAMAÑO_TABLERO && columna + j >= 0 && columna + j < TAMAÑO_TABLERO) {
                        if (tablero[fila + i][columna + j].numeroDeMinasAdyacentes == 0) {
                            tablero[fila + i][columna + j].estaDestapada = true
                            for (a in -1..1) {
                                for (b in -1..1) {
                                    if (fila + i + a >= 0 && fila + i + a < TAMAÑO_TABLERO && columna + j + b >= 0 && columna + j + b < TAMAÑO_TABLERO) {
                                        if (tablero[fila + i + a][columna + j + b].numeroDeMinasAdyacentes != 0) {
                                            tablero[fila + i + a][columna + j + b].estaDestapada = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun menu(): Int {
        println("[1] Depejar una casilla")
        println("[2] Marcar una casilla con bandera")
        var opcion = 0
        do{
            try{
                opcion = readln().toInt()
                opcionValida(opcion)
            }catch(e: Exception){
                println(e.message)
                opcion = 0
            }
        }while(opcion == 0)
        return opcion
    }

    fun opcionValida(opcion: Int): Boolean {
        require(opcion in 1..2){"No has elegido una opción válida, vuelve a probar:"}
        return true
    }

    fun siguenMinasOcultas(): Boolean {
        for(i in tablero.indices){
            for(j in tablero[i].indices){
                if(tablero[i][j].esUnaMina == true && tablero[i][j].estaDestapada == false && tablero[i][j].estaMarcadaCoUnBandera == false){
                    return true
                }
            }
        }
        return false
    }

    fun ningunaMinaHaEstallado(): Boolean {
        for(i in tablero.indices){
            for(j in tablero[i].indices){
                if(tablero[i][j].esUnaMina == true && tablero[i][j].estaDestapada == true && tablero[i][j].estaMarcadaCoUnBandera == false){
                    return false
                }
            }
        }
        return true
    }

    fun representarMatriz(){
        println("  1 2 3 4 5 6 7 8")
        var mensaje = ""
        for(i in tablero.indices){
            mensaje = "${i+1}"
            for(j in tablero[i].indices){
                if(tablero[i][j].estaMarcadaCoUnBandera == true) {
                    mensaje = "$mensaje B"
                }else{
                    if (tablero[i][j].estaDestapada == true) {
                        if(tablero[i][j].esUnaMina == true){
                            mensaje = "$mensaje *"
                        }else {
                            mensaje = "$mensaje ${tablero[i][j].numeroDeMinasAdyacentes}"
                        }
                    } else {
                        mensaje = "$mensaje X"
                    }
                }
            }
            println(mensaje)
            mensaje = ""
        }
    }

    fun introducirPosicion(): Pair<Int, Int> {
        var fila = 0
        do {
            println("Introduce la fila:")
            try {
                fila = readln().toInt()-1
                filaColumnaValida(fila)
            }catch (e: Exception){
                println(e.message)
                fila = -1
            }
        }while(fila == -1)
        var columna = 0
        do {
            println("Introduce la columna:")
            try {
                columna = readln().toInt()-1
                filaColumnaValida(columna)
            }catch (e: Exception){
                println(e.message)
                columna = -1
            }
        }while(columna == -1)
        return Pair(fila, columna)
    }

    fun filaColumnaValida(filaColumna: Int): Boolean {
        require(filaColumna in (0..7)){"El valor introducido es inválido, solo puede estra entre 1 y 8."}
        return true
    }
}