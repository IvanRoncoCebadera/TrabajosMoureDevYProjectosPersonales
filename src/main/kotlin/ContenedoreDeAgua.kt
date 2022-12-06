fun main(){

    var matriz = Array(5){IntArray(5)}

    var bloques = ""
    do {
        try {
            println("Introduce los bloques que habra en la matriz (5,0,1,3,4 es un ejemplo de como ponerlos):")
            bloques = readln()
            esValidoBloques(bloques)
        } catch (e: Exception) {
            println(e.message)
            bloques = ""
        }
    }while(bloques == "")

    println("El envase formado con los bloques introducidos queda así:")
    println()

    matriz = colocarBloques(bloques, matriz)

    println()
    println("Al añadir las gotas al recipiente queda así:")
    println()

    matriz = colocarGotasAgua(matriz)

    println()

    println("En el recipiente con esos bloques, hay un total de ${calcularNumeroGotasDeAgua(matriz)} gotas de agua.")
}

fun colocarGotasAgua(matriz: Array<IntArray>): Array<IntArray> {
    var condicion1 = false
    var condicion2 = false
    for(i in 0..4){
        for(j in 0..4){
            if(matriz[i][j] != 1) {
                condicion1 = false
                condicion2 = false
                for(a in j .. 4){
                    if(matriz[i][a] == 1){
                        condicion1 = true
                    }
                }
                for(b in 0 .. j){
                    if(matriz[i][b] == 1){
                        condicion2 = true
                    }
                }
                if (condicion1 == true && condicion2 == true) {
                    matriz[i][j] = 9
                }
            }
        }
    }
    representarMatriz(matriz)
    return matriz
}

fun colocarBloques(bloques: String, matriz: Array<IntArray>): Array<IntArray> {
    var bloque: List<String> = bloques.split(",")
    var numero = 0
    for(i in 0..4){
        numero = bloque[i].toInt()
        if(numero != 0) {
            val resto = 5 - numero
            for (j in 4 downTo resto) {
                matriz[j][i] = 1
            }
        }
    }
    representarMatriz(matriz)
    return matriz
}

fun representarMatriz(matriz: Array<IntArray>) {
    var mensaje = ""
    for(i in matriz.indices){
        for(j in matriz[i].indices){
            mensaje = "$mensaje ${matriz[i][j]}"
        }
        println(mensaje)
        mensaje = ""
    }
}

fun calcularNumeroGotasDeAgua(matriz: Array<IntArray>): Int {
    var gotas = 0
    for(i in matriz.indices){
        for(j in matriz[i].indices){
            if(matriz[i][j] == 9) {
                gotas++
            }
        }
    }
    return gotas
}

fun esValidoBloques(bloques: String) {
    val regex = Regex("[0-5],[0-5],[0-5],[0-5],[0-5]")
    require(bloques.matches(regex)){"No has elegido bien los bloques"}
}
