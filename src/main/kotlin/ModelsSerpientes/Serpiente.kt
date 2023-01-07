package ModelsSerpientes

class Serpiente {

    var cuerpo = ""

    init{
        añadirDiscoColorAleatorio()
    }

    fun simulacionVidaSerpiente(){
        println("Al inicio el cuerpo de la serpiente es:")
        println(cuerpo)
        var añosDeVida = 1
        do{
            var chance = (1..100).random()
            if(chance <= 90) {
                chance = (1..100).random()
                println("No a habido ningún ataque de mangosta")
                if (añosDeVida <= 10) {
                    if (chance <= 80) {
                        añadirDiscoColorAleatorio()
                        println("En el año de vida $añosDeVida de la serpiente, su cuerpo aumento y ahora se ve así:")
                        println(cuerpo)
                        añosDeVida += esperarUnSegundo()
                    } else {
                        mudarLaPiel()
                        println("En el año de vida $añosDeVida de la serpiente, esta mudo la piel y ahora se ve así:")
                        println(cuerpo)
                        añosDeVida += esperarUnSegundo()
                    }
                } else {
                    if (chance <= 90) {
                        disminuirTamañoCuerpoSerpiente()
                        println("En el año de vida $añosDeVida de la serpiente, su cuerpo disminuyo su tamaño y ahora se ve así:")
                        println(cuerpo)
                        añosDeVida += esperarUnSegundo()
                    } else {
                        mudarLaPiel()
                        println("En el año de vida $añosDeVida de la serpiente, esta mudo la piel y ahora se ve así:")
                        println(cuerpo)
                        añosDeVida += esperarUnSegundo()
                    }
                }
            }else{
                println("Ha aparecido una mangosta que mató a la serpiente")
                cuerpo = ""
            }
        }while(laSerpienteEstaViva())
        println("La serpiente fallecio por lo que efe.")
    }

    fun disminuirTamañoCuerpoSerpiente() {
        var cuerpoAuxiliar = ""
        for(i in 1 until cuerpo.length){
            cuerpoAuxiliar = "${cuerpoAuxiliar}${cuerpo[i]}"
        }
        cuerpo = cuerpoAuxiliar
    }

    fun esperarUnSegundo(): Int {
        Thread.sleep(1000)
        return 1
    }

    fun mudarLaPiel() {
        val tamañoSerpiente = cuerpo.length
        cuerpo = ""
        for(i in 1..tamañoSerpiente){
            añadirDiscoColorAleatorio()
        }
    }

    fun añadirDiscoColorAleatorio(){
        when((1..3).random()){
            1 -> cuerpo = "r${cuerpo}"
            2 -> cuerpo = "v${cuerpo}"
            3 -> cuerpo = "a${cuerpo}"
        }
    }

    fun laSerpienteEstaViva(): Boolean{
        return if(cuerpo != ""){
            true
        }else{
            false
        }
    }
}