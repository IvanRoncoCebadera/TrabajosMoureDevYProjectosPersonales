package models;

import lombok.Data;

import javax.inject.Inject;
import javax.inject.Singleton;

@Data
public class Trabajador {

    private String nombre;
    private int salario;

    @Inject

    public Trabajador(String nombre, int salario) {
        this.nombre = nombre;
        this.salario = salario;
    }
}
