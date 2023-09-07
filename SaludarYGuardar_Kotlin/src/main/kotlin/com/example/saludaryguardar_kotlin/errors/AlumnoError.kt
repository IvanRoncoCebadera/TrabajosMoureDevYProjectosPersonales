package com.example.saludaryguardar_kotlin.errors

sealed class AlumnoError(val message: String){
    class AlumnoNotFound(message: String): AlumnoError("El alumno "+message+", no fue encontrado.")
    class NombreNoValido(message: String): AlumnoError("El nombre del alumno '"+message+"', no es válido.")
    class EdadNoEsUnNumero(message: String): AlumnoError("La edad del alumno '"+message+"', debe ser un número")
    class EdadNoValido(message: Int, min: Int): AlumnoError("La edad del alumno "+message+", no es válida, debe ser como mínimo "+min+".")
    class EmailNoValido(message: String): AlumnoError("El email del alumno '"+message+"', no es válido.")
}
