package com.example.saludaryguardar_java.repository;

import com.example.saludaryguardar_java.models.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnoRepository {

    private List<Alumno> alumnos = new ArrayList<>();

    public List<Alumno> getAll(){
        return alumnos;
    }

    public Alumno save(Alumno alumno){
        if(getAll().stream().filter(it -> it.getEmail().equals(alumno.getEmail())).count() > 0){
            update(alumno);
        }else{
            create(alumno);
        }
        return alumno;
    }

    private void create(Alumno alumno) {
        alumnos.add(alumno);
    }

    private void update(Alumno alumno) {
        alumnos.remove(getAll().stream().filter(it -> it.getEmail().equals(alumno.getEmail())).findFirst().get());
        alumnos.add(alumno);
    }
}
