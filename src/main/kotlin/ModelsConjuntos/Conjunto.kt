package ModelsConjuntos

data class Conjunto(val conjuntoA: Array<Char>, val conjuntoB: Array<Char>) {

    fun interseccion(): Array<Char>{
        var conjuntoC = Array<Char>(conjuntoA.size){' '}
        var contador = 0
        for(i in conjuntoA){
            for(j in conjuntoB){
                if(i == j && letraNoRepetida(i, conjuntoC)){
                    conjuntoC[contador] = i
                    contador++
                }
            }
        }
        return conjuntoC
    }

    fun letraNoRepetida(letra: Char, conjunto: Array<Char>): Boolean {
        for(i in conjunto){
            if(i.equals(letra)){
                return false
            }
        }
        return true
    }

    fun union(): Array<Char>{
        var conjuntoC = Array<Char>(conjuntoA.size){' '}
        var contador = 0
        for(i in conjuntoA.indices){
            if(letraNoRepetida(conjuntoA[i], conjuntoC)){
                conjuntoC[contador] = conjuntoA[i]
                contador++
            }
            if(letraNoRepetida(conjuntoB[i], conjuntoC)){
                conjuntoC[contador] = conjuntoB[i]
                contador++
            }
        }
        return conjuntoC
    }

    fun cardinalidadDelConjuntoDeseado(){
        println("""Selecciona si quieres hallar la cardinalidad del conjunto "A" o del conjunto "B":""")
        when(introducirEleccion()){
            "a" -> {
                println("La cardinalidad del conjunto A")
                cardinalidad(conjuntoA)
            }
            "b" -> {
                println("La cardinalidad del conjunto B")
                cardinalidad(conjuntoB)
            }
        }
    }

    fun cardinalidad(conjunto: Array<Char>) {
        var contadorDeLetrasDiferentes = 0
        var conjuntoAuxiliar = Array<Char>(conjunto.size){' '}
        for(i in conjunto){
            if(letraNoRepetida(i, conjuntoAuxiliar)){
                conjuntoAuxiliar[contadorDeLetrasDiferentes] = i
                contadorDeLetrasDiferentes++
            }
        }
        conjuntoAuxiliar = ordenarAlfabeticamente(conjuntoAuxiliar)

        println("Con un total de $contadorDeLetrasDiferentes letras distintas en total:")
        println(mostrarConjunto(conjuntoAuxiliar))
    }

    fun ordenarAlfabeticamente(conjunto: Array<Char>): Array<Char> {
        var conjuntoAuxiliar = IntArray(conjunto.size)
        for(i in 0 until conjunto.size){
            conjuntoAuxiliar[i] = conjunto[i].toInt()
        }

        for(i in 0 until conjuntoAuxiliar.size - 1){
            for(j in 0 until conjuntoAuxiliar.size - 1 - i){
                if(conjuntoAuxiliar[j] > conjuntoAuxiliar[j + 1]){
                    val auxiliar = conjuntoAuxiliar[j]
                    conjuntoAuxiliar[j] = conjuntoAuxiliar[j + 1]
                    conjuntoAuxiliar[j + 1] = auxiliar
                }
            }
        }

        for(i in 0 until conjunto.size){
            conjunto[i] = conjuntoAuxiliar[i].toChar()
        }

        return conjunto
    }

    fun introducirEleccion(): String {
        var eleccion = ""
        do{
            try{
                eleccion = readln().trim().lowercase()
                eleccionValida(eleccion)
            }catch(e: Exception){
                println(e.message)
                eleccion = ""
            }
        }while(eleccion == "")
        return eleccion
    }

    fun eleccionValida(eleccion: String): Boolean {
        require(eleccion.isNotEmpty()){"La eleccion no puede estar vacia, vuelve a probar:"}
        require(eleccion == "a" || eleccion == "b"){"No has elegido una de las opciones posibles, vuelve a probar:"}
        return true
    }

    fun mostrarConjuntos(){
        println("El conjunto A es:")
        println(mostrarConjunto(conjuntoA))
        println("El conjunto B es:")
        println(mostrarConjunto(conjuntoB))
    }

    fun mostrarConjunto(conjunto: Array<Char>): String {
        var mensaje: StringBuilder = StringBuilder()
        for (i in conjunto.indices) {
            if(conjunto[i] != ' ') {
                if (i != conjunto.size - 1) {
                    mensaje.append("${conjunto[i]}, ")
                } else {
                    mensaje.append(conjunto[i])
                }
            }
        }
        return mensaje.toString()
    }
}