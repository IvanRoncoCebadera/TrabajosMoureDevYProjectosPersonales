package models;

import lombok.Data;

import javax.inject.Inject;
import javax.inject.Singleton;

@Data
public class Persona {
    private Trabajador trabajo;

    @Inject
    public Persona(Trabajador trabajo) {
        this.trabajo = trabajo;
    }
}
