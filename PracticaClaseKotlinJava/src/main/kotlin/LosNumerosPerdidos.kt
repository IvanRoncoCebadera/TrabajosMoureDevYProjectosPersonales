fun main(){
    var numeros: IntArray = IntArray(10)

    inicializarElVectorNumeros(numeros)

    enseñarVector(numeros)

    mostrarTodosLosNúmerosFaltantesEntreElMenorYElMayorDelVectorNumeros(numeros)
}

/**
 * función que muestra una lista con todos los números perdidios entre el menor y el mayor del vectro numeros
 * @param numeros el vector del que sacamos los numeros a representar
 */
fun mostrarTodosLosNúmerosFaltantesEntreElMenorYElMayorDelVectorNumeros(numeros: IntArray) {
    println("Los números que faltan entre el menor y el mayor del vector son:")
    for(i in (numeros[0]..numeros[9])){
        if(numeroNoRepetido(i, numeros)){
            println("El número: $i")
        }
    }
}

/**
 * función que sirve para averiguar si el número se encuentra entre aquellos elegidos al azar para el vector números
 * @param numero es el número que comprobaremos si ya está en el vector números o no
 * @param numeros es el vector del que sacaremos los diferentes números para comparar con el número pasado por parámetro
 * @return true si no existe ese número en el vector, false si si existe
 */
fun numeroNoRepetido(numero: Int, numeros: IntArray): Boolean {
    for(i in numeros){
        if(i == numero){
            return false
        }
        if(i > numero){
            break
        }
    }
    return true
}

/**
 * función que muestra en mensaje los números con los que se han inicializado el vector de números
 * @param numeros el vector del que sacamos los numeros a representar
 */
fun enseñarVector(numeros: IntArray) {
    var mensaje = StringBuilder()
    for(i in numeros.indices){
        if(i != numeros.size - 1) {
            mensaje.append("${numeros[i]}, ")
        }else{
            mensaje.append(numeros[i])
        }
    }
    println("Los números del vector de números son:")
    println(mensaje)
}

/**
 * función que inicializa el vector numeros con números elegidos al azar que no se repiten y ordenados
 * @param numeros es el vector de números que se inicializa
 */
fun inicializarElVectorNumeros(numeros: IntArray) {
    var azar = 0
    for(i in numeros.indices){
        azar = azar + (1..10).random()
        numeros[i] = azar
    }
}
