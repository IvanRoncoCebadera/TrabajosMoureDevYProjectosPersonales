package repositories

import Especialidad
import models.IChapista
import models.IElectricista
import models.Trabajador
import java.util.*

class TrabajadorRepositoryMemory: TrabajadorRepository {

    val incremento = 3
    var trabajadores: Array<Trabajador?> = Array<Trabajador?>(incremento){null}

    override fun getAll(): Array<Trabajador> {
        val array = Array<Trabajador?>(numeroTrabajadores()){null}
        var contador = 0
        for(trabajador in trabajadores){
            if(trabajador != null) {
                array[contador] = trabajador
                contador++
            }
        }
        return array as Array<Trabajador>
    }

    override fun findById(id: UUID): Trabajador? {
        var trabajadorEncontrado: Trabajador? = null
        if(numeroTrabajadores() != 0){
            for(trabajador in trabajadores){
                if(trabajador != null && trabajador.id == id){
                    trabajadorEncontrado = trabajador
                    break
                }
            }
        }
        return trabajadorEncontrado
    }

    override fun create(trabajador: Trabajador): Trabajador? {
        if(numeroTrabajadores() == trabajadores.size){
            redimensionarArray(incremento)
        }
        var nuevoTrabajador: Trabajador? = null
        if(trabajadorNoExistente(trabajador)) {
            trabajadores[numeroTrabajadores()] = trabajador
            nuevoTrabajador = trabajador
        }
        return nuevoTrabajador
    }

    override fun update(id: UUID, trabajador: Trabajador): Trabajador? {
        var trabajadorActualizado: Trabajador? = null
        if(numeroTrabajadores() != 0){
            for(i in trabajadores.indices){
                if(trabajadores[i] != null && trabajadores[i]!!.id == id){
                    trabajadores[i] = trabajador

                    trabajadorActualizado = trabajador

                    break
                }
            }
        }
        return trabajadorActualizado
    }

    override fun delete(id: UUID): Trabajador? {
        var trabajador: Trabajador? = null
        if(numeroTrabajadores() != 0){
            for(i in trabajadores.indices){
                if(trabajadores[i] != null && trabajadores[i]!!.id == id){
                    trabajador = trabajadores[i]
                    trabajadores[i] = null

                    if((trabajadores.size - numeroTrabajadores()) >= incremento) {
                        redimensionarArray(-incremento)
                    }

                    break
                }
            }
        }
        return trabajador
    }

    override fun findByEspecialidad(especialidad: Especialidad): Array<Trabajador> {
        return when(especialidad){
            Especialidad.CHAPISTA -> buscarChapistas()
            Especialidad.ELECTRICISTA -> buscarElectricistas()
            Especialidad.CHAPISTA_ELECTRICISTA -> buscarChapistasElectricistas()
        }
    }

    override fun deleteAll() {
        for(trabajador in trabajadores){
            if(trabajador != null) delete(trabajador.id)
        }
    }

    override fun safeAll(trabajadores: Array<Trabajador?>) {
        for (trabajador in trabajadores) {
            if(trabajador != null) create(trabajador)
        }
    }

    /**
     * función que sirve para contar el número de trabajadores presentes en el array
     * @return el numero de trabajadores que hay
     */
    private fun numeroTrabajadores(): Int {
        var contador = 0
        for(trabajador in trabajadores){
            if(trabajador != null) contador++
        }
        return contador
    }

    /**
     * función que sirve para redimensionar el array de trabajadores, según el tamaño que se pase por parametro
     * @param tamaño es la cantidad con la que redimensionaremos el arrya, positiva y se ampliara, negativa y se restara
     */
    private fun redimensionarArray(tamaño: Int) {
        val array = Array<Trabajador?>(trabajadores.size + tamaño){null}
        var contador = 0
        for(trabajador in trabajadores){
            if(trabajador != null){
                array[contador] = trabajador
                contador++
            }
        }
        trabajadores = array
    }

    private fun buscarChapistasElectricistas(): Array<Trabajador> {
        var contador = 0
        for(trabajador in trabajadores){
            if(trabajador != null && trabajador is Trabajador.ChapistaElectricista) contador++
        }
        val array = Array<Trabajador?>(contador){null}
        contador = 0
        for(trabajador in trabajadores){
            if(trabajador != null && trabajador is Trabajador.ChapistaElectricista){
                array[contador] = trabajador
                contador++
            }
        }
        return array as Array<Trabajador>
    }

    private fun buscarElectricistas(): Array<Trabajador> {
        var contador = 0
        for(trabajador in trabajadores){
            if(trabajador != null && trabajador is IElectricista) contador++
        }
        val array = Array<Trabajador?>(contador){null}
        contador = 0
        for(trabajador in trabajadores){
            if(trabajador != null && trabajador is IElectricista){
                array[contador] = trabajador
                contador++
            }
        }
        return array as Array<Trabajador>
    }

    private fun buscarChapistas(): Array<Trabajador> {
        var contador = 0
        for(trabajador in trabajadores){
            if(trabajador != null && trabajador is IChapista) contador++
        }
        val array = Array<Trabajador?>(contador){null}
        contador = 0
        for(trabajador in trabajadores){
            if(trabajador != null && trabajador is IChapista){
                array[contador] = trabajador
                contador++
            }
        }
        return array as Array<Trabajador>
    }

    private fun trabajadorNoExistente(trabajador: Trabajador): Boolean {
        for(trabajadorComaracion in trabajadores){
            if(trabajadorComaracion != null && trabajadorComaracion.id == trabajador.id) return false
        }
        return true
    }

}