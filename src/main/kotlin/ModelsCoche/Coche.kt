package ModelsCoche

import java.util.ConcurrentModificationException
import kotlin.concurrent.thread

data class Coche(val marca: String, val modelo: String, val motor: String, val matricula: String, val velocidadPedida: Int, val tiempoPedido: Int) {

    var motorEncendido = false

    var marchaActual = 1

    var velocidadActual = 0

    fun encenderMotor(){
        println("Se enciende el motor")
        motorEncendido = true
    }

    fun apagarMotor(){
        println("Se apaga el motor")
        motorEncendido = false
    }

    fun cambiarMarcha(){
        when(velocidadActual){
            in(0..29) -> {
                if(marchaActual != 1){
                    println("Se ha cambiado la marcha a marcha 1")
                }
                marchaActual = 1
            }
            in(30..49) -> {
                if(marchaActual != 2){
                    println("Se ha cambiado la marcha a marcha 2")
                }
                marchaActual = 2
            }
            in(50..69) -> {
                if(marchaActual != 3){
                    println("Se ha cambiado la marcha a marcha 3")
                }
                marchaActual = 3
            }
            in(70..99) -> {
                if(marchaActual != 4){
                    println("Se ha cambiado la marcha a marcha 4")
                }
                marchaActual = 4
            }
            in(100..999) -> {
                if(marchaActual != 5){
                    println("Se ha cambiado la marcha a marcha 5")
                }
                marchaActual = 5
            }
        }
    }

    fun aumentarVelocidad(contadorTiempo: Int){
        if(velocidadActual < velocidadPedida - 10) {
            velocidadActual += 10
            println("Se está acelerando: $velocidadActual km/h")
            cambiarMarcha()
        }else{
            if(velocidadActual in (velocidadPedida - 10..velocidadPedida - 1)){
                velocidadActual += (velocidadPedida - velocidadActual)
                println("Ya casi estamos en la velocidad deseada: $velocidadActual km/h")
                cambiarMarcha()
            }else{
                println("Ya se ha alcanzado la velocidad deseada: $velocidadActual km/h, se ha tardado un total de $contadorTiempo segundos")
                cambiarMarcha()
            }
        }
    }

    fun disminuirVelocidad(contadorTiempo: Int){
        if(velocidadActual > 10) {
            velocidadActual -= 10
            println("Se está frenando: $velocidadActual km/h")
            cambiarMarcha()
        }else{
            if(velocidadActual in (1..10)){
                velocidadActual = 0
                println("Se está terminando de detener el vehículo: $velocidadActual km/h")
                cambiarMarcha()
            }else{
                println("Ya se ha detenido el vehículo: $velocidadActual km/h, se ha tardado un total de $contadorTiempo segundos")
                cambiarMarcha()
            }
        }
    }

    fun esperarUnSegundo(): Int{
        Thread.sleep(1000)
        return 1
    }

    fun simuladorDeCoche(){
        var contadorTiempo = 0
        encenderMotor()
        do{
            aumentarVelocidad(contadorTiempo)
            contadorTiempo += esperarUnSegundo()
        }while(contadorTiempo < tiempoPedido)
        contadorTiempo = 0
        do {
            disminuirVelocidad(contadorTiempo)
            contadorTiempo += esperarUnSegundo()
        }while(velocidadActual != 0)
        apagarMotor()
    }
}