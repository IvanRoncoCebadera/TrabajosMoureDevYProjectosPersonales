package ModelsMiPropioJuegoDeNavecitas

import ModelsMiPropioJuegoDeNavecitas1.Luke

class Espacio {

    var matrizObjetos: Array<Array<Any?>> = Array(10){Array<Any?>(10){null} }

    var navesEnemigas: Array<Enemigos?> = Array<Enemigos?>(9){null}

    var asteroides: Array<Asteroides?> = Array<Asteroides?>(4){null}

    var luke: Luke = Luke(combustible = 0, municion = 0, vida = 0)

    var navesAbatidas = 0

    /**
     * funcion que muestra la matriz espacio
     */
    fun mostrarEspacio(matrizEspacio: Array<Array<String>>){
        var mensaje = ""
        for(i in matrizEspacio.indices){
            for(j in matrizEspacio[i].indices){
                mensaje = "$mensaje ${matrizEspacio[i][j]}"
            }
            println(mensaje)
            mensaje = ""
        }
    }

    /**
     * función que actualizar la representación de Luke y las naves enemigas en el espacio
     * @param luke es el objeto Luke
     * @return la matriz auxiliar actualizada
     */
    fun actualizarEspacio(matrizEspacio: Array<Array<String>>, luke: Luke): Array<Array<String>>{
        var auxiliar = Array(10){Array<String>(10){" "} }
        for(i in matrizObjetos.indices){
            for(j in matrizObjetos[i].indices){
                if(matrizObjetos[i][j] == luke){
                    auxiliar[i][j] = luke.marca
                }else{
                    if(matrizObjetos[i][j] == buscarNaveEnemiga(i, j)){
                        auxiliar[i][j] = buscarIdNaveEnemigaPosicion(i, j)
                    }else{
                        if(matrizObjetos[i][j] == buscarAsteroide(matrizEspacio, i, j)){
                            auxiliar[i][j] = "*"
                        }
                    }
                }
            }
        }
        return auxiliar
    }

    /**
     * función que busca el id de la nave enemiga segun la posición en el vector de navesEnemigas y en la matrizObjetos
     * @param fila valor de fila de la matrizObjetos
     * @param columna valor de columna de la matrizObjetos
     * @return el id de la nave enemiga
     */
    fun buscarIdNaveEnemigaPosicion(fila: Int, columna: Int): String {
        for(i in navesEnemigas.indices){
            if(navesEnemigas[i] != null) {
                if (navesEnemigas[i] == matrizObjetos[fila][columna]) {
                    return navesEnemigas[i]!!.id.toString()
                }
            }
        }
        return " "
    }

    fun buscarNaveEnemiga(fila: Int, columna: Int): Enemigos? {
        var pos = 0
        for(i in navesEnemigas.indices){
            if(navesEnemigas[i] == matrizObjetos[fila][columna]){
                pos = i
                break
            }
        }
        return navesEnemigas[pos]?: navesEnemigas[0]
    }

    /**
     * funcion que emula cojer los suministros de los asteroides si nuestra nave se coloco sobre uno de ellos
     */
    fun recogerSuministros(matrizEspacio: Array<Array<String>>, fila: Int, columna: Int, luke: Luke) {
        if (matrizEspacio[fila][columna] == "*") {
            println("Estás sobre un asteroide, así que compruebas si tiene suministros:")
            var asteroide: Asteroides? = buscarAsteroide(matrizEspacio,fila, columna)
            println("has conseguido:")
            println("Munición: ${luke.municion} (que ya tenías) + ${asteroide!!.municion} (por parte del asteroide)")
            println("Combustible: ${luke.combustible} (que ya tenías) + ${asteroide!!.suministro} (por parte del asteroide)")
            luke.municion += asteroide!!.municion
            luke.combustible += asteroide!!.suministro
        }
    }

    fun buscarAsteroide(matrizEspacio: Array<Array<String>>, fila: Int, columna: Int): Asteroides? {
        var pos = 0
        for(i in asteroides.indices){
            if("*" == matrizEspacio[fila][columna]){
                pos = i
                break
            }
        }
        return asteroides[pos]?: asteroides[0]
    }

    /**
     * función que sirve para inicializar la matriz espacio con el contenido necesario
     * @param matrizEspacio es la matriz espacio vacia con la que trabajaremos
     * @return la matriz con los objetos para averiguar que objeto está en cada casilla
     */
    fun iniciarEspacio(matrizEspacio: Array<Array<String>>, luke: Luke): Array<Array<String>>{
        var auxiliar = matrizEspacio
        var fila = 0
        var columna = 0
        for(i in asteroides.indices){
            do{
                fila = (0..9).random()
                columna = (0..9).random()
            }while(auxiliar[fila][columna] != " ")
            matrizObjetos[fila][columna] = asteroides[i]
            auxiliar[fila][columna] = asteroides[i]!!.marca
        }
        for(i in navesEnemigas.indices){
            if(navesEnemigas[i] != null){
                do{
                    fila = (0..9).random()
                    columna = (0..9).random()
                }while(auxiliar[fila][columna] != " ")
                matrizObjetos[fila][columna] = navesEnemigas[i]
                auxiliar[fila][columna] = navesEnemigas[i]!!.id.toString()
            }
        }
        do{
            fila = (0..9).random()
            columna = (0..9).random()
        }while(auxiliar[fila][columna] != " ")
        matrizObjetos[fila][columna] = luke
        auxiliar[fila][columna] = luke.marca
        return auxiliar
    }

    /**
     * funcion que muestra las distintas opciones de dificultada que hay, y recoge la opción elegida por el usuario
     * @return la opcion que eligió el usuario tras ser validada
     */
    fun modoDificultad(): Int{
        var opcion = 0
        do {
            println("[1] Facil")
            println("[2] Normal")
            println("[3] Dificil")
            println("[0] Salir")
            try{
                opcion = readln().toInt()
                opcionValida(opcion)
            }catch(e: Exception){
                println(e.message)
                opcion = -1
            }
        }while(opcion == -1)
        return opcion
    }

    /**
     * función que sirve para validar la opción que ha ingresado el usuario
     * @param opcion la opción que ha introducido el usuario
     * @return true en caso de que sea correcto, una excepción en caso contrario
     */
    fun opcionValida(opcion: Int): Boolean {
        require(opcion in 0..3){"No has elegido una opción disponible, vuelva a probar"}
        return true
    }

    fun recibirDisparoLuke(){
        var azar = (1..100).random()
        if(azar in 1..20){
            println("Maldición as recibido un tiro por parte de la nave: ${navesEnemigas[(0..8).random()]?: navesEnemigas[0]}")
            luke.vida--
            println("Te quedán ${luke.vida} puntos de vida")
        }else{
            println("Has evitado todos los disparos enemigos bien hecho, aun te quedán ${luke.vida} puntos de vida")
        }
    }

    /**
     * funcon que sirve para saber si luke dio a alguien y en caso afirmativo, quitar vida a la nave a la que dio
     */
    fun disparoLuke(matrizEspacio: Array<Array<String>>, criticoDificultad: Int){
        var numeroEnemigos = 0
        var auxiliar = matrizEspacio
        var fila = luke.encontrarLuke(matrizObjetos).split("-")[0].toInt()
        var columna = luke.encontrarLuke(matrizObjetos).split("-")[1].toInt()
        for(i in -2..2){
            for(j in -2..2){
                if(fila + i < 10 && fila + i >= 0 && columna + j < 10 && columna + j >= 0){
                    if(luke.municion != 0) {
                        if (golpearEnemigo(criticoDificultad, fila + i, columna + j)) {
                            numeroEnemigos++
                            luke.municion--
                        }
                    }else{
                        println("No te queda munición para efectuar más disparos")
                        break
                    }
                }
            }
        }
        println("Has golpeado a un total de $numeroEnemigos enemigos en está ronda, bajandole un punto de vida a cada uno.")
    }

    fun golpearEnemigo(criticoDificultad: Int, fila: Int, columna: Int): Boolean {
        var resultado = false
        for(i in navesEnemigas.indices){
            if(navesEnemigas[i] != null) {
                if (navesEnemigas[i] == matrizObjetos[fila][columna]) {
                    resultado = true
                    navesEnemigas[i]!!.vida--
                    println("La nave ${navesEnemigas[i]!!.id} fue golpeada, ahora les quedán ${navesEnemigas[i]!!.vida} puntos de vida")
                    if(disparoCritico(criticoDificultad)){
                        println("Pero tuviste suerte y fue un disparo crítico a la nave ${navesEnemigas[i]} por lo que la avatiste de inmediato")
                        quitarNaveEspacio(navesEnemigas[i])
                        navesAbatidas++
                    }else {
                        if (navesEnemigas[i]!!.vida == 0) {
                            quitarNaveEspacio(navesEnemigas[i])
                            navesAbatidas++
                        }
                    }
                    break
                }
            }
        }
        return resultado
    }

    fun quitarNaveEspacio(enemigos: Enemigos?) {
        for(i in matrizObjetos.indices){
            for(j in matrizObjetos[i].indices){
                if(matrizObjetos[i][j] == enemigos){
                    matrizObjetos[i][j] = null
                }
            }
        }
    }

    fun estado(){
        println("Munición restante: ${luke.municion}")
        println("Combustible restante: ${luke.combustible}")
    }

    /**
     * funcion que sirve para calcular si acertamos un disparo critico o no
     * @param criticoDificultad es chance de acertar un critico según la dificultad elegida
     * return true si el disparo es critico, false si no lo es
     */
    fun disparoCritico(criticoDificultad: Int): Boolean{
        var critico = (1..100).random()
        return if(critico in 1..criticoDificultad){
            true
        }else{
            false
        }
    }

    /**
     * funcion de desplazamiento de las naves enemigas segun una direccion aleatoria y el numero de casillas a moverse aleatoriamente
     * @param espacio es la matriz sobre la que se encontrara toda la info
     * @param direccion la direccion en donde nos moveremos
     * @param desplazamiento la cantidad de casillas maximas que se podra mover la nave al avanzar
     */
     //La razón por la que está función está aquí y no en la clase Enemigos, es porque al principio intente algo más complicado que requería colocarlo aquí
     //Sin embargo, de la manera que termine planteando el ejercicio al final no era necesario colocarlo aquí
    fun desplazarEnemigos(matrizEspacio: Array<Array<String>> , luke: Luke){
        var contador = 0
        var vectorEnemigos: Array<Any?> = Array<Any?>(10){null}
        var movimiento = 0
        var direccion = 0
        for(i in matrizObjetos.indices){
            for(j in matrizObjetos[i].indices){
                if(matrizObjetos[i][j] != buscarAsteroide(matrizEspacio, i, j) && matrizObjetos[i][j] != luke && matrizObjetos[i][j] != null) {
                    var fila = i
                    var columna = j
                    if (noSeRepiteObjeto(vectorEnemigos, i, j)) {
                        do {
                            movimiento = (1..9).random()
                            direccion = (1..4).random()
                            if (direccion == 1) {
                                while (movimiento != 0) {
                                    if (columna <= 8) {
                                        columna++
                                        movimiento--
                                    } else {
                                        break
                                    }
                                }
                            } else {
                                if (direccion == 2) {
                                    while (movimiento != 0) {
                                        if (columna >= 1) {
                                            columna--
                                            movimiento--
                                        } else {
                                            break
                                        }
                                    }
                                } else {
                                    if (direccion == 3) {
                                        while (movimiento != 0) {
                                            if (fila >= 1) {
                                                fila--
                                                movimiento--
                                            } else {
                                                break
                                            }
                                        }
                                    } else {
                                        while (movimiento != 0) {
                                            if (fila <= 8) {
                                                fila++
                                                movimiento--
                                            } else {
                                                break
                                            }
                                        }
                                    }
                                }
                            }
                        } while (matrizObjetos[fila][columna] != null)
                        vectorEnemigos[contador] = matrizObjetos[i][j]
                        contador++
                        var objetoAuxiliar = matrizObjetos[i][j]
                        matrizObjetos[i][j] = null
                        matrizObjetos[fila][columna] = objetoAuxiliar
                    }
                }
            }
        }
    }

    private fun noSeRepiteObjeto(vectorEnemigos: Array<Any?>, fila: Int, columna: Int): Boolean {
        for(i in vectorEnemigos.indices){
            if(vectorEnemigos[i]?.equals(matrizObjetos[fila][columna])?: false){
                return false
            }
        }
        return true
    }

    /**
     * funcion de desplazamiento de luke segun la direccion elegida y el numero de casillas elegidas
     * @param espacio es la matriz sobre la que se encontrara toda la info
     * @param direccion la direccion en donde nos moveremos
     * @param desplazamiento la cantidad de casillas maximas que se podra mover la nave al avanzar
     */
     //La razón por la que está función está aquí y no en la clase Luke, es la misma de porque el movimiento de los enemigos está en la clase Espacio
    fun desplazarLuke(matrizEspacio: Array<Array<String>>, espacio: Array<Array<Any?>>, direccion: String, desplazamiento: Int){
        var movimiento = desplazamiento
        var auxiliar = espacio
        var fila = luke.encontrarLuke(espacio).split("-")[0].toInt()
        var columna = luke.encontrarLuke(espacio).split("-")[1].toInt()
        var objetoAuxiliar = auxiliar[fila][columna]
        auxiliar[fila][columna] = null
        if (direccion == "derecha") {
            while (movimiento != 0) {
                if (columna <= 8) {
                    columna++
                    movimiento--
                } else {
                    println("Ya no se puede continuar por ese camino")
                    break
                }
            }
        } else {
            if (direccion == "izquierda") {
                while (movimiento != 0) {
                    if (columna >= 1) {
                        columna--
                        movimiento--
                    } else {
                        println("Ya no se puede continuar por ese camino")
                        break
                    }
                }
            } else {
                if (direccion == "arriba") {
                    while (movimiento != 0) {
                        if (fila >= 1) {
                            fila--
                            movimiento--
                        } else {
                            println("Ya no se puede continuar por ese camino")
                            break
                        }
                    }
                } else {
                    while (movimiento != 0) {
                        if (fila <= 8) {
                            fila++
                            movimiento--
                        } else {
                            println("Ya no se puede continuar por ese camino")
                            break
                        }
                    }
                }
            }
        }
        if(auxiliar[fila][columna] != null && auxiliar[fila][columna] != buscarAsteroide(matrizEspacio, fila, columna)){
            println("En esa posición hay una nave enemiga, vas a tener que continuar un poco más, así que vuelva a introducir datos de dirección y desplazamiento, como si no te hubieras movido:")
            var datos = luke.introducirDireccionDesplazamiento()
            var direccion = datos.split(";")[0]
            var desplazamiento = datos.split(";")[1].toInt()
            desplazarLuke(matrizEspacio, espacio, direccion, desplazamiento)
        }
        auxiliar[fila][columna] = objetoAuxiliar
        luke.combustible -= (desplazamiento - movimiento)
    }
}
