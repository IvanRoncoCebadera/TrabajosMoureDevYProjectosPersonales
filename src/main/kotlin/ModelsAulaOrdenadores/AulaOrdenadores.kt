package ModelsAulaOrdenadores

data class AulaOrdenadores(val identificadorAula: String, var curso: String) {

    var ordenadores = Array<Ordenadores?>(20){null}
    
    fun añadirOrdenadorAlAula(){
        val posicion = buscarPrimeraPosicionLibre()
        if(posicion != -1){
            println("Se va ha introducir el ordenador: ${crearOrdenador(posicion)}")
        }else{
            println("No queda ningún espacio más para colocar ordenadores en el aula")
        }
    }

    fun crearOrdenador(posicion: Int): Ordenadores? {
        val ordenador: Ordenadores? = Ordenadores()
        ordenadores[posicion] = ordenador
        return ordenador
    }

    fun sacarOrdenadorDelAula(){
        println("Introduce el id del ordenador que quiere sacar del aula:")
        val id = introducirId()
        val posicion = buscarPosicionPorId(id)
        if(posicion != -1){
            println("Se va ha sacado del aula el ordenador: ${eliminarOrdenador(posicion)}")
        }else{
            println("No hay ningún ordenador en el aula con esa id")
        }
    }

    fun eliminarOrdenador(posicion: Int): Ordenadores? {
        val ordenador = ordenadores[posicion]
        ordenadores[posicion] = null
        return ordenador
    }

    fun actualizarOrdenadorDelAula(){
        println("Introduce el id del ordenador que quiere actualizar del aula:")
        val id = introducirId()
        val posicion = buscarPosicionPorId(id)
        if(posicion != -1){
            println("Se va ha actualizado del aula el ordenador: ${actualizarOrdenador(posicion)}, al ordenador: ${ordenadores[posicion]}")
        }else{
            println("No hay ningún ordenador en el aula con esa id")
        }
    }

    fun actualizarOrdenador(posicion: Int): Ordenadores? {
        val ordenador = ordenadores[posicion]
        ordenadores[posicion] = Ordenadores()
        return ordenador
    }

    fun mostrarTodosLosOrdenadores(){
        ordenarOrdeznadoresPorId()
        println("Hay un total de ${totalDeOrdenadoresEnElAula()} ordenadores en el aula, y son:")
        for(i in ordenadores){
            if(i != null){
                println(i)
            }
        }
    }

    fun mostrarUnOrdenadorPorId(){
        println("Introduce el id del ordenador del aula que quiere ver:")
        val id = introducirId()
        val posicion = buscarPosicionPorId(id)
        if(posicion != -1){
            println(ordenadores[posicion])
        }else{
            println("No hay ningún ordenador en el aula con esa id")
        }
    }

    fun totalDeOrdenadoresEnElAula(): Int {
        var contador = 0
        for(i in ordenadores){
            if(i != null){
                contador++
            }
        }
        return contador
    }

    fun ordenarOrdeznadoresPorId() {
        for(i in ordenadores.indices){
            for(j in ordenadores.indices - i){
                if(ordenadores[j] != null && ordenadores[j+1] != null){
                    if(ordenadores[j]!!.id > ordenadores[j+1]!!.id){
                        val auxiliar = ordenadores[j]
                        ordenadores[j] = ordenadores[j+1]
                        ordenadores[j+1] = auxiliar
                    }
                }
            }
        }
    }

    fun introducirId(): Int {
        var id = 0
        do{
            try{
                id = readln().toInt()
            }catch(e: Exception){
                println(e.message)
                id = -1
            }
        }while(id <= 0)
        return id
    }

    fun buscarPosicionPorId(id: Int): Int {
        var posicion = -1
        for(i in ordenadores.indices){
            if(ordenadores[i] != null){
                if(ordenadores[i]!!.id == id) {
                    posicion = i
                    break
                }
            }
        }
        return posicion
    }


    fun buscarPrimeraPosicionLibre(): Int {
        var posicion = -1
        for(i in ordenadores.indices){
            if(ordenadores[i] == null){
                posicion = i
                break
            }
        }
        return posicion
    }
}