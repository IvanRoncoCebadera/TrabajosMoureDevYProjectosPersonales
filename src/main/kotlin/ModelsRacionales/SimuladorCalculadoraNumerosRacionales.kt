package ModelsRacionales

fun main() {
    println("Introduce la fraccion de a:")
    val a = leer()
    println("Introduce la fraccion de b:")
    val b = leer()

    var racionales = Racionales(a,b)
    var opcion = 0
    do {
        println("Selecciona la opcion que desees:")
        println("[1] Introducir fraccion")
        println("[2] Sumar")
        println("[3] Multiplicar")
        println("[0] Salir")
        opcion = readln().toInt()
        when(opcion){
            1 -> racionales.leer()
            2 -> println("El resultado de la suma es: ${racionales.sumar()}")
            3 -> println("El resultado de la multiplicacion es: ${racionales.multiplicar()}")
        }
    }while(opcion in (0..3))
    println("Adios...")
}

fun leer(): String{
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
    return fraccion
}

fun fraccionValida(fraccion: String): Boolean {
    val regex = Regex("[0-9]+/[0-9]+")
    require(fraccion.matches(regex)){"No has introducido una fraccion válida, vuelve a probar:"}
    val divisor = fraccion.split("/")[1].toInt()
    require(divisor != 0){"El divisor de la fraccion no puede ser 0, vuelva a probar:"}
    return true
}