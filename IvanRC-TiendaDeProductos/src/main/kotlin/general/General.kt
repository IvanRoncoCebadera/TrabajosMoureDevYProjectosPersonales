package general

import models.Producto
import java.lang.Exception
import java.util.DoubleSummaryStatistics

class General {
    companion object {

        /**
         * función que válida si el nombre introducido es válido o no
         * @throws IllegalArgumentException si el nombre no es válido
         * @return true si el nombre si es válido
         */
        fun String.validarContenido(): Boolean {
            require(this.isNotEmpty()) {
                "Error al introducir el contenido: El contenido no puede estar vacio, vuelva a probar:"
            }
            return true
        }

        /**
         * función que nos permite introducir cualquier número
         * @return el número validado
         */
        fun introducirNumero(min: Int, max: Int = 9999): String {
            var numero = ""
            do {
                try {
                    numero = readln().trim()
                    numero.validarNumero(min, max)
                } catch (e: Exception) {
                    numero = ""
                    println(e.message)
                }
            } while (numero == "")
            return numero
        }

        /**
         * función que sirve para validar si el numero decimal elegido es válido o no
         * @param min el valor minimo que puede tomar el numero decimal
         * @throws IllegalArgumentException en caso de que el numero decimal no sea válido
         * @return true en caso de que el numero decimal sea válido
         */
        fun String.validarNumero(min: Int, max: Int): Boolean {
            require(this.isNotEmpty()) {
                "Error al introducir datos: El dato del usuario no puede estar vacio, vuelva aprobar:"
            }
            val regex = Regex("[0-9]*")
            require(this.matches(regex)) {
                "Error al introducir datos: El dato del usuario debe ser un número positivo, vuelva aprobar:"
            }
            require(this.toInt() in (min..max as Int)) {
                "Error al introducir datos: El dato del usuario debe ser un número que como mínimo valga $min y coma maximo $max, vuelva aprobar:"
            }
            return true
        }

        /**
         * función que nos permite introducir cualquier número
         * @return el número validado
         */
        fun introducirNumeroDecimal(min: Double, max: Double = 9999.9): String {
            var numero = ""
            do {
                try {
                    numero = readln().trim()
                    numero.validarNumeroDecimal(min, max)
                } catch (e: Exception) {
                    numero = ""
                    println(e.message)
                }
            } while (numero == "")
            return numero
        }

        /**
         * función que sirve para validar si el numero decimal elegido es válido o no
         * @param min el valor minimo que puede tomar el numero decimal
         * @throws IllegalArgumentException en caso de que el numero decimal no sea válido
         * @return true en caso de que el numero decimal sea válido
         */
        fun String.validarNumeroDecimal(min: Double, max: Double): Boolean {
            require(this.isNotEmpty()) {
                "Error al introducir datos: El dato del usuario no puede estar vacio, vuelva aprobar:"
            }
            val regex = Regex("[0-9]*.[0-9]*?")
            require(this.matches(regex)) {
                "Error al introducir datos: El dato del usuario debe ser un número positivo, vuelva aprobar:"
            }
            require(this.toDouble() in (min..max as Double)) {
                "Error al introducir datos: El dato del usuario debe ser un número que como mínimo valga $min y coma maximo $max, vuelva aprobar:"
            }
            return true
        }

        /**
         * función que nos permite introducir el contenido deseado
         * @return el contenido validado
         */
        fun introducirContenido(): String {
            var nombre = ""
            do {
                try {
                    nombre = readln().trim()
                    nombre.validarContenido()
                } catch (e: Exception) {
                    nombre = ""
                    println(e.message)
                }
            } while (nombre == "")
            return nombre
        }
    }
}