package org.example;

import di.component.DaggermyComponent;
import models.Persona;

public class Main {
    public static void main(String[] args) {

        Persona persona = DaggermyComponent.create().build();

        System.out.println(persona.getTrabajo().toString());

        System.out.println("Hello world!");
    }
}