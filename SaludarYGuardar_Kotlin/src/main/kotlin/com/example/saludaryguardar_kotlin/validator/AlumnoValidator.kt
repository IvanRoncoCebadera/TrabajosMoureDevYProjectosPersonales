package com.example.saludaryguardar_kotlin.validator

import com.example.saludaryguardar_kotlin.errors.AlumnoError
import com.example.saludaryguardar_kotlin.models.Alumno
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import mu.KotlinLogging

private val logger = KotlinLogging.logger {  }

fun validateAlumno(nombre: String, edad: String, email: String): Result<Alumno, AlumnoError>{
    logger.debug { "Validamos el alumno" }
    require(nombre.isNotEmpty()){
        return Err(AlumnoError.NombreNoValido(nombre))
    }
    val edadNum = edad.toIntOrNull()
    require(edadNum != null){
        return Err(AlumnoError.EdadNoEsUnNumero(edad))
    }
    val min = 18
    require(edadNum >= min){
        return Err(AlumnoError.EdadNoValido(edadNum, min))
    }
    val emailPatter = Regex("[a-zA-Z0-9]*@gmail.com")
    require(email.isNotEmpty() && email.matches(emailPatter)){
        return Err(AlumnoError.EmailNoValido(email))
    }
    return Ok(
        Alumno(
        nombre,
        edadNum,
        email
    )
    )
}