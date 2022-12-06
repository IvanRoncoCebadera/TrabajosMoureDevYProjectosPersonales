import ModelsMiPropioJuegoDeNavecitas.Asteroides
import ModelsMiPropioJuegoDeNavecitas.Enemigos
import ModelsMiPropioJuegoDeNavecitas.Espacio
import ModelsMiPropioJuegoDeNavecitas1.Luke

fun main(){
    var espacio = Espacio()
    var matrizEspacio: Array<Array<String>> = Array(10){Array<String>(10){" "} }
    var opcion = 0
    var vidaLuke = 0
    var combustibleLuke = 0
    var municionLuke = 0
    var criticoDificultad = 0
    var municionAsteroides = 0
    var suminisTroAsteroide = 0

    println("Por favor, seleccione la dificultad que desea:")
    opcion = espacio.modoDificultad()
    when(opcion){
        1 -> {
            vidaLuke = 20
            combustibleLuke = 30
            municionLuke = 30
            espacio.luke = Luke(combustible = combustibleLuke, municion = municionLuke, vida = vidaLuke)
            criticoDificultad = 20
            for (i in 0..6) {
                espacio.navesEnemigas[i] = Enemigos()
            }
            municionAsteroides = (10..20).random()
            suminisTroAsteroide = (10..20).random()
            for (i in espacio.asteroides.indices) {
                espacio.asteroides[i] =
                    Asteroides(municion = municionAsteroides, suministro = suminisTroAsteroide)
            }
            matrizEspacio = espacio.iniciarEspacio(matrizEspacio, espacio.luke)
            println("El espacio al principio se encuentra así:")
            espacio.mostrarEspacio(matrizEspacio)
            println()
            do{
                println("""Introduzca la dirección y la cantidad de casillas que se desplazará Luke(0), debe respetar el formato: "derecha;4"""")
                var datos = espacio.luke!!.introducirDireccionDesplazamiento()
                var direccion = datos.split(";")[0]
                var desplazamiento = datos.split(";")[1].toInt()
                espacio.desplazarLuke(matrizEspacio, espacio.matrizObjetos, direccion, desplazamiento)


                var fila = espacio.luke.encontrarLuke(espacio.matrizObjetos).split("-")[0].toInt()
                var columna = espacio.luke.encontrarLuke(espacio.matrizObjetos).split("-")[1].toInt()
                espacio.recogerSuministros(matrizEspacio, fila, columna, espacio.luke)
                println()

                espacio.desplazarEnemigos(matrizEspacio, espacio.luke)

                espacio.disparoLuke(matrizEspacio, criticoDificultad)
                println()

                espacio.recibirDisparoLuke()
                println()

                espacio.estado()
                println()

                println("Llevas abatidas ${espacio.navesAbatidas} naves enemigas")
                println()

                matrizEspacio = espacio.actualizarEspacio(matrizEspacio, espacio.luke)

                println("El espacio se encuentra así:")
                espacio.mostrarEspacio(matrizEspacio)
                println()
                Thread.sleep(2_000L)
            }while(espacio.navesAbatidas == 7 && espacio.luke.vida <= 0 && espacio.luke.combustible <= 0)
        }
        2 -> {
            vidaLuke = 15
            combustibleLuke = 20
            municionLuke = 20
            espacio.luke = Luke(combustible = combustibleLuke, municion = municionLuke, vida = vidaLuke)
            criticoDificultad = 10
            for (i in 0..6) {
                espacio.navesEnemigas[i] = Enemigos()
            }
            municionAsteroides = (5..15).random()
            suminisTroAsteroide = (5..15).random()
            for (i in espacio.asteroides.indices) {
                espacio.asteroides[i] = Asteroides(municion = municionAsteroides, suministro = suminisTroAsteroide)
            }
            matrizEspacio = espacio.iniciarEspacio(matrizEspacio, espacio.luke)
            println("El espacio al principio se encuentra así:")
            espacio.mostrarEspacio(matrizEspacio)
            println()
            do{
                println("""Introduzca la dirección y la cantidad de casillas que se desplazará Luke(0), debe respetar el formato: "derecha;4"""")
                var datos = espacio.luke!!.introducirDireccionDesplazamiento()
                var direccion = datos.split(";")[0]
                var desplazamiento = datos.split(";")[1].toInt()
                espacio.desplazarLuke(matrizEspacio, espacio.matrizObjetos, direccion, desplazamiento)


                var fila = espacio.luke.encontrarLuke(espacio.matrizObjetos).split("-")[0].toInt()
                var columna = espacio.luke.encontrarLuke(espacio.matrizObjetos).split("-")[1].toInt()
                espacio.recogerSuministros(matrizEspacio, fila, columna, espacio.luke)
                println()

                espacio.desplazarEnemigos(matrizEspacio, espacio.luke)

                espacio.disparoLuke(matrizEspacio, criticoDificultad)
                println()

                espacio.recibirDisparoLuke()
                println()

                espacio.estado()
                println()

                println("Llevas abatidas ${espacio.navesAbatidas} naves enemigas")
                println()

                matrizEspacio = espacio.actualizarEspacio(matrizEspacio, espacio.luke)

                println("El espacio se encuentra así:")
                espacio.mostrarEspacio(matrizEspacio)
                println()
                Thread.sleep(2_000L)
            }while(espacio.navesAbatidas == 7 && espacio.luke.vida <= 0 && espacio.luke.combustible <= 0)
        }
        3 -> {
            vidaLuke = 10
            combustibleLuke = 10
            municionLuke = 10
            espacio.luke = Luke(combustible = combustibleLuke, municion = municionLuke, vida = vidaLuke)
            criticoDificultad = 5
            for (i in 0..8) {
                espacio.navesEnemigas[i] = Enemigos()
            }
            municionAsteroides = (0..10).random()
            suminisTroAsteroide = (0..10).random()
            for (i in espacio.asteroides.indices) {
                espacio.asteroides[i] =
                    Asteroides(municion = municionAsteroides, suministro = suminisTroAsteroide)
            }
            matrizEspacio = espacio.iniciarEspacio(matrizEspacio, espacio.luke)
            println("El espacio al principio se encuentra así:")
            espacio.mostrarEspacio(matrizEspacio)
            println()
            do{
                println("""Introduzca la dirección y la cantidad de casillas que se desplazará Luke(0), debe respetar el formato: "derecha;4"""")
                var datos = espacio.luke!!.introducirDireccionDesplazamiento()
                var direccion = datos.split(";")[0]
                var desplazamiento = datos.split(";")[1].toInt()
                espacio.desplazarLuke(matrizEspacio, espacio.matrizObjetos, direccion, desplazamiento)


                var fila = espacio.luke.encontrarLuke(espacio.matrizObjetos).split("-")[0].toInt()
                var columna = espacio.luke.encontrarLuke(espacio.matrizObjetos).split("-")[1].toInt()
                espacio.recogerSuministros(matrizEspacio, fila, columna, espacio.luke)
                println()

                espacio.desplazarEnemigos(matrizEspacio, espacio.luke)

                espacio.disparoLuke(matrizEspacio, criticoDificultad)
                println()

                espacio.recibirDisparoLuke()
                println()

                espacio.estado()
                println()

                println("Llevas abatidas ${espacio.navesAbatidas} naves enemigas")
                println()

                matrizEspacio = espacio.actualizarEspacio(matrizEspacio, espacio.luke)

                println("El espacio se encuentra así:")
                espacio.mostrarEspacio(matrizEspacio)
                println()
                Thread.sleep(2_000L)
            }while(espacio.navesAbatidas == 9 && espacio.luke.vida <= 0 && espacio.luke.combustible <= 0)
        }
    }
    println("Se termino el juego:")
    if(espacio.luke.vida <= 0){
        println("El enemigo terminó matandote")
    }else{
        if(espacio.luke.combustible <= 0){
            println("Te has quedado tirado por el espacio sin combustible, debiste haber agarrado más suministros de los asteroides(*)")
        }else{
            println("Has acabado con todos los enemigos felicidades")
        }
    }
}
