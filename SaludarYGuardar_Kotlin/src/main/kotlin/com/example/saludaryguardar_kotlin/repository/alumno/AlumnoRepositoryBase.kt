package com.example.saludaryguardar_kotlin.repository.alumno

import com.example.saludaryguardar_kotlin.models.Alumno
import com.example.saludaryguardar_kotlin.repository.base.CrudRepository

interface AlumnoRepositoryBase: CrudRepository<Alumno, String> {}