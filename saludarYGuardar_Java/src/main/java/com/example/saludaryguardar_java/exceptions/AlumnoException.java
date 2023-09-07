package com.example.saludaryguardar_java.exceptions;

public sealed class AlumnoException extends IllegalArgumentException {
    public AlumnoException(String s) {
        super(s);
    }

    public static final class NombreException extends AlumnoException{
        public NombreException(String s) {
            super("El nombre '"+s+"', no es correcto.");
        }
    }
    public static final class EdadException extends AlumnoException{
        public EdadException(String s) {
            super("La edad '"+s+"', no es correcta.");
        }
    }
    public static final class EmailException extends AlumnoException{
        public EmailException(String s) {
            super("El email '"+s+"', no es correcto.");
        }
    }
}
