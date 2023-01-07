package ModelsMosca

class EspacioParaMosca {

    var espacio = Array(8){Array<Mosca>(8){Mosca(10, "Negra")} }

    fun simuladorJuegoMosca(){
        espacio[(0..7).random()][(0..7).random()].estaLaMosca = true

        do {
            println("Introduce la posicion de la casilla que desea golpear:")
            val posicionGolpeada = introducirPosicionAGolpear()
            println()
            when(analizarGolpe(posicionGolpeada)){
                1 -> println("Felicidades, mataste a la mosca, por lo que has ganado el juego")
                2 -> {
                    println("Estuviste tan cerca de golpear a la mosca que esta salio volando")
                    reiniciarEspacio()
                }
                3 -> println("No estuviste ni cerca de golpear a la mosca")
            }
            println()
            println("El espacio está así:")
            representarMatriz()
            println()
        }while(quedaAlgunaMoscaViva())
    }

    fun reiniciarEspacio() {
        for(i in espacio.indices){
            for(j in espacio[i].indices){
                espacio[i][j] = Mosca(10, "Negra")
            }
        }
        espacio[(0..7).random()][(0..7).random()].estaLaMosca = true
    }

    fun quedaAlgunaMoscaViva(): Boolean {
        for(i in espacio.indices){
            for(j in espacio[i].indices){
                if(espacio[i][j].estaLaMosca == true) {
                    return true
                }
            }
        }
        return false
    }

    fun analizarGolpe(posicionGolpeada: Pair<Int, Int>): Int{
        var caso = 0
        val fila = posicionGolpeada.first
        val columna = posicionGolpeada.second
        if(espacio[fila][columna].estaLaMosca == true){
            caso = 1
            espacio[fila][columna].estaLaMosca = false
        }else{
            if(estaLaMoscaCerca(posicionGolpeada)){
                caso = 2
            }else{
                caso = 3
            }
        }
        return caso
    }

    fun estaLaMoscaCerca(posicionGolpeada: Pair<Int, Int>): Boolean {
        val fila = posicionGolpeada.first
        val columna = posicionGolpeada.second
        for(i in -1..1){
            for(j in -1..1){
                if(fila + i >= 0 && fila + i < 8 && columna + j >= 0 && columna + j < 8) {
                    if (espacio[fila + i][columna + j].estaLaMosca == true) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun introducirPosicionAGolpear(): Pair<Int, Int> {
        var fila = 0
        do {
            println("Introduce la fila donde desea golpear:")
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
            println("Introduce la columna donde desea golpear:")
            try {
                columna = readln().toInt()-1
                filaColumnaValida(columna)
            }catch (e: Exception){
                println(e.message)
                columna = -1
            }
        }while(columna == -1)
        espacio[fila][columna].casillaGolpeada = true
        return Pair(fila, columna)
    }

    fun filaColumnaValida(filaColumna: Int): Boolean {
        require(filaColumna in (0..7)){"El valor introducido es inválido, solo puede estra entre 1 y 8."}
        return true
    }

    fun representarMatriz(){
        println("  1 2 3 4 5 6 7 8")
        var mensaje = ""
        for(i in espacio.indices){
            mensaje = "${i+1}"
            for(j in espacio[i].indices){
                if(espacio[i][j].casillaGolpeada == true) {
                    mensaje = "$mensaje x"
                }else{
                    mensaje = "$mensaje O"
                }
            }
            println(mensaje)
            mensaje = ""
        }
    }
}