fun main(){

    var ataque = introducirAtaque()

    var tipo = introducirTipo("Introduce el tipo de tu pokemon (fuego, agua, planta, electrico):")

    var defensa = introducirDefensa()

    var tipoEn = introducirTipo("Introduce el tipo del pokemon enemigo (fuego, agua, planta, electrico):")

    println("El ataque hara un total de ${calcularDaño(ataque, defensa, calcularEfectividad(tipo, tipoEn))} puntos de daño")

}

fun calcularEfectividad(tipo: String, tipoEn: String): Double {
    if(tipo == "agua"){
        if(tipoEn == "planta"){
            return 0.5
        }else{
            if(tipoEn == "fuego"){
                return 2.0
            }else{
                return 1.0
            }
        }
    }else{
        if(tipo == "planta"){
            if(tipoEn == "fuego"){
                return 0.5
            }else{
                if(tipoEn == "agua"){
                    return 2.0
                }else{
                    return 1.0
                }
            }
        }else{
            if(tipo == "fuego"){
                if(tipoEn == "agua"){
                    return 0.5
                }else{
                    if(tipoEn == "planta"){
                        return 2.0
                    }else{
                        return 1.0
                    }
                }
            }else{
                if(tipoEn == "planta"){
                    return 0.5
                }else{
                    if(tipoEn == "agua"){
                        return 2.0
                    }else{
                        return 1.0
                    }
                }
            }
        }
    }
}

fun introducirTipo(mensaje: String): String {
    var tipo = ""
    do {
        try {
            print(mensaje)
            tipo = readln().trim().lowercase()
            esValidoTipo(tipo)
        } catch (e: Exception) {
            println(e.message)
            tipo = ""
        }
    } while (tipo == "")
    return tipo
}

fun esValidoTipo(tipo: String) {
    require(tipo == "agua" || tipo == "planta" || tipo == "fuego" || tipo == "electrico")
}

fun introducirDefensa(): Int {
    var defensa = 0
    do {
        try {
            print("Por favor, introduzca la defensa del pokemon enemigo(de 1 a 100):")
            defensa = readln().toInt()
            esValido(defensa)
        } catch (e: Exception) {
            println(e.message)
            defensa = -1
        }
    } while (defensa == -1)
    return defensa
}

fun introducirAtaque(): Int {
    var ataque = 0
    do {
        try {
            print("Por favor, introduzca el ataque de su pokemon(de 1 a 100):")
            ataque = readln().toInt()
            esValido(ataque)
        } catch (e: Exception) {
            println(e.message)
            ataque = -1
        }
    } while (ataque == -1)
    return ataque
}

fun esValido(numero: Int) {
    require(numero in 1..100){"Ese valor no es válido"}
}

fun calcularDaño(ataque: Int, defensa: Int, efectividad: Double): Double{
    return (50*(ataque/defensa)*efectividad)
}