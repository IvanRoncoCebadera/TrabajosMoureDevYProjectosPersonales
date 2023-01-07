package modelsReloj

fun main(){
    println("""Introduce la hora actual, en el formato: "hh:mm:ss":""")
    val hora: String = introducirHora()
    val tiempo = hora.split(":")
    val horas = tiempo[0].toInt()
    val minutos = tiempo[1].toInt()
    val segundos = tiempo[2].toInt()
    val reloj = Reloj(horas, minutos, segundos)
    println("Tienes un total de ${reloj.bateria}% de bateria")
    var opcion = 0
    do{
        println("Seleccione la opción que desea:")
        opcion = menu()
        when(opcion){
            1 -> println(reloj)
            2 -> reloj.avanzarHora()
            3 -> reloj.recargarBateria()
        }
        println("Te queda un total de ${reloj.bateria}% de bateria")
    }while(opcion != 0 && reloj.bateria > 0)
    println("Adios..")
}

fun menu(): Int {
    var opcion = 0
    do {
        println("[1] Mostrar hora")
        println("[2] Avanzar hora")
        println("[3] Recargar pila")
        println("[0] Salir")
        opcion = readln().toInt()
    }while(opcion !in (0..3))
    return opcion
}

fun introducirHora(): String {
    var hora = ""
    do{
        try{
            hora = readln().trim()
            horaValida(hora)
        }catch(e: Exception){
            println(e.message)
            hora = ""
        }
    }while(hora == "")
    return hora
}

fun horaValida(hora: String): Boolean {
    val regex = Regex("[0-9]{2}:[0-9]{2}:[0-9]{2}")
    require(hora.matches(regex)){"La hora introducida no es válida, vuelva a probar:"}
    val tiempo = hora.split(":")
    val horas = tiempo[0].toInt()
    require(horas in (0..23)){"La hora introducida debe estar entre 00 y 23, vuelva a probar:"}
    val minutos = tiempo[1].toInt()
    require(minutos in (0..59)){"Los minutos introducida debe estar entre 00 y 59, vuelva a probar:"}
    val segundos = tiempo[2].toInt()
    require(segundos in (0..59)){"Los segundos introducida debe estar entre 00 y 59, vuelva a probar:"}
    return true
}
