package ModelsRacionales

data class Racionales(var a: String, var b: String) {

    fun leer(){
        var fraccion = ""
        do{
            try{
                fraccion = readln().trim()
                fraccionValida(fraccion)
            }catch(e: Exception){
                println(e.message)
                fraccion = ""
            }
        }while(fraccion == "")
        var opcion = ""
        do {
            println("""¿Quieres que esa fraccion se la de "a" o la de "b"?""")
            opcion = readln().trim()
        }while(opcion != "a" && opcion != "b")
        if(opcion == "a"){
            this.a = fraccion
        }else{
            this.b = fraccion
        }
    }

    fun sumar(): String{
        println("Se va ha sumar las fracciones $a y $b")
        val a = this.a.split("/")
        val numeradorA = a[0].toInt()
        val divisorA = a[1].toInt()
        val b = this.b.split("/")
        val numeradorB = b[0].toInt()
        val divisorB = b[1].toInt()

        var resultadoDivisor = divisorA * divisorB
        var resultadoNumerador = (numeradorA * divisorB) + (numeradorB * divisorA)

        return simplificar("$resultadoNumerador/$resultadoDivisor")
    }

    fun multiplicar(): String{
        println("Se va ha multiplicar las fracciones $a y $b")
        val a = this.a.split("/")
        val numeradorA = a[0].toInt()
        val divisorA = a[1].toInt()
        val b = this.b.split("/")
        val numeradorB = b[0].toInt()
        val divisorB = b[1].toInt()

        val resultadoNumerador = numeradorA * divisorB
        val resultadoDivisor = numeradorB * divisorA

        return simplificar("$resultadoNumerador/$resultadoDivisor")
    }

    fun simplificar(s: String): String {
        var resultado = s
        val b = s.split("/")
        val numerador = b[0].toInt()
        val divisor = b[1].toInt()
        if(numerador % divisor == 0){
            resultado = "${numerador/divisor}"
        }else{
            if(divisor % numerador == 0){
                resultado = "1/${numerador/divisor}"
            }else{
                for(i in 2..9) {
                    if (numerador % i == 0 && divisor % i == 0) {
                        resultado = "${numerador / i}/${divisor / i}"
                        break
                    }
                }
            }
        }
        return resultado
    }

    fun fraccionValida(fraccion: String): Boolean {
        val regex = Regex("[0-9]+/[0-9]+")
        require(fraccion.matches(regex)){"No has introducido una fraccion válida, vuelve a probar:"}
        val divisor = fraccion.split("/")[1].toInt()
        require(divisor != 0){"El divisor de la fraccion no puede ser 0, vuelva a probar:"}
        return true
    }
}