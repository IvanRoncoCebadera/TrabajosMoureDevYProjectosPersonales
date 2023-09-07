package models;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Persona {
    @NotNull
    String nombre;
    @NotNull
    int edad;
    @NotNull
    Estudios estudios;
    @Nullable
    String localidad;

    public Persona(String nombre, int edad, Estudios estudios, @NotNull String localidad) {
        this.nombre = nombre;
        this.edad = edad;
        this.estudios = estudios;
        this.localidad = localidad;
    }
    @NotNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @NotNull
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    @NotNull
    public Estudios getEstudios() {
        return estudios;
    }

    public void setEstudios(Estudios estudios) {
        this.estudios = estudios;
    }

    @Nullable
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(@NotNull String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", estudios=" + estudios +
                ", localidad='" + localidad + '\'' +
                '}';
    }

    public enum Estudios {
        DAM, DAW, ASIR
    }
}

