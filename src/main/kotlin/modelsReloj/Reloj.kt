package modelsReloj

data class Reloj(var hora: Int, var minuto: Int, var segundo: Int) {

    var bateria: Int = (6..100).random()

    override fun toString(): String {
        var horas: String = ""
        if(this.hora == 0){
            horas = "00"
        }else {
            if (this.hora in 1..9) {
                horas = "0${this.hora}"
            } else {
                horas = "${this.hora}"
            }
        }
        var minutos: String = ""
        if(this.minuto == 0){
            minutos = "00"
        }else {
            if (this.minuto in 1..9) {
                minutos = "0${this.minuto}"
            } else {
                minutos = "${this.minuto}"
            }
        }
        var segundos: String = ""
        if(this.segundo == 0){
            segundos = "00"
        }else {
            if (this.segundo in 1..9) {
                segundos = "0${this.segundo}"
            } else {
                segundos = "${this.segundo}"
            }
        }
        this.bateria = this.bateria - 5
        if(this.bateria < 0){
            this.bateria = 0
        }
        return "La hora que marcas el reloj es: $horas:$minutos:$segundos"
    }

    /**
     * función que nos permite recargar la bateria de nuestro reloj
     */
    fun recargarBateria(){
        bateria = 100
    }

    fun avanzarHora() {
        this.bateria = this.bateria - 10
        if(this.bateria < 0){
            this.bateria = 0
        }
        this.segundo++
        if(this.segundo == 60){
            this.segundo = 0
            this.minuto++
            if(this.minuto == 60){
                this.minuto = 0
                this.hora++
                if(this.hora == 24){
                    this.hora = 0
                }
            }
        }
    }
}