package ModelsConjuntos

fun main(){
    println("Nota: Los dos conjuntos de letras con los que se realizarán la simulaxción se creán de manera automática y aleatoria cada vez, pewro siempre  será de tamaño 27.")

    val conjuntos = Conjunto(crearConjunto(), crearConjunto())

    var opcion = 0
    do{
        println("Seleccione la opcion que desea:")
        opcion = menu()
        when(opcion){
            1 -> conjuntos.mostrarConjuntos()
            2 -> {
                println("La intersección entre ambos conjuntos es:")
                println(conjuntos.mostrarConjunto(conjuntos.interseccion()))
            }
            3 -> {
                println("La unión entre ambos conjuntos es:")
                println(conjuntos.mostrarConjunto(conjuntos.union()))
            }
            4 -> conjuntos.cardinalidadDelConjuntoDeseado()
        }
    }while(opcion != 0)
}

fun crearConjunto(): Array<Char> {
    val letras = "abcdefghijklmnñopqrstuvwxyz"
    var conjunto = Array<Char>(27){' '}
    for(i in conjunto.indices){
        conjunto[i] = letras[(0..26).random()]
    }
    return conjunto
}

fun menu(): Int{
    var opcion = 0
    do {
        println("[1] Mostrar ambos conjuntos de letras")
        println("[2] Mostrar la intersección entre ambos conjuntos de letras")
        println("[3] Mostrar la unión de ambos conjuntos de letras")
        println("[4] Mostrar la cardinalidad de un conjunto de letras")
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

fun opcionValida(opcion: Int): Boolean {
    require(opcion in 0..4){"No has elegido una opción disponible, vuelva a probar"}
    return true
}
