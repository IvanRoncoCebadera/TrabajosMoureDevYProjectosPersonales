package com.example.saludaryguardar_java.validator;

import com.example.saludaryguardar_java.exceptions.AlumnoException;
import com.example.saludaryguardar_java.models.Alumno;

public class AlumnoValidator {
    public static Alumno validateAlumno(String nombre, String edad, String email){
        if(nombre.isEmpty()){
            throw new AlumnoException.NombreException(nombre);
        }
        Integer edadNum = 0;
        try{
            edadNum = Integer.parseInt(edad);
        }catch (Exception e){
            edadNum = null;
        }
        if(edadNum == null || edadNum < 18){
            throw new AlumnoException.EdadException(edad);
        }
        var emailPattern = "[a-zA-Z0-9]*@gmail.com";
        if(email.isEmpty() || !email.matches(emailPattern)){
            throw new AlumnoException.EmailException(email);
        }
        return new Alumno(nombre, edadNum, email);
    }
}
