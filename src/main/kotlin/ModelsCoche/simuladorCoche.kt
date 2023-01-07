package ModelsCoche

fun main(){
    println("Introduce la marca del coche:")
    val marca = introducirMarcaModeloMotor()
    println("Introduce el modelo del coche:")
    val modelo = introducirMarcaModeloMotor()
    println("Introduce el motor del coche:")
    val motor = introducirMarcaModeloMotor()
    println("Introduce la matricula del coche:")
    val matricula = introducirMatricula()
    println("Introduce la velocidad que desea alcanzar con el coche:")
    val velocidad = introducirVelocidad()
    println("Introduce el tiempo que desea dejar que el coche acelere:")
    val tiempo = introducirTiempo()

    val coche = Coche(marca, modelo, motor, matricula, velocidad, tiempo)

    coche.simuladorDeCoche()
}

fun introducirVelocidad(): Int{
    var velocidad = 0
    do{
        try{
            velocidad = readln().toInt()
        }catch(e: Exception){
            println(e.message)
            velocidad = -1
        }
    }while(velocidad < 0)
    return velocidad
}

fun introducirTiempo(): Int{
    var tiempo = 0
    do{
        try{
            tiempo = readln().toInt()
        }catch(e: Exception){
            println(e.message)
            tiempo = -1
        }
    }while(tiempo < 0)
    return tiempo
}

fun introducirMarcaModeloMotor(): String{
    var marcaModeloMotor = ""
    do{
        try{
            marcaModeloMotor = readln().trim()
            marcaModeloMotorValido(marcaModeloMotor)
        }catch(e: Exception){
            println(e.message)
            marcaModeloMotor = ""
        }
    }while(marcaModeloMotor == "")
    return marcaModeloMotor
}

fun marcaModeloMotorValido(marcaModeloMotor: String): Boolean {
    require(marcaModeloMotor != null){"El dato introducido no puede ser nula, vuelve a probar:"}
    require(marcaModeloMotor.isNotEmpty()){"El dato introducido no puede estar vacia, vuelve a probar:"}
    return true
}

fun introducirMatricula(): String {
    var matricula = ""
    do{
        try{
            matricula = readln().trim()
            matriculaValida(matricula)
        }catch(e: Exception){
            println(e.message)
            matricula = ""
        }
    }while(matricula == "")
    return matricula
}

fun matriculaValida(matricula: String): Boolean {
    require(matricula != null){"La matricula introducido no puede ser nula, vuelve a probar:"}
    require(matricula.isNotEmpty()){"La matricula introducido no puede estar vacia, vuelve a probar:"}
    val regex = Regex("[0-9]{4}-[A-Z]{3}")
    require(matricula.matches(regex)){"La matricula introducido no cumple con los patrones esperados ( 0000-AAA ), vuelve a probar:"}
    return true
}