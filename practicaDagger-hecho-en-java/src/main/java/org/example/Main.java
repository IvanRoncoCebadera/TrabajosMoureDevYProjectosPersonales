package org.example;

import di.component.DaggerComponent;
import models.coche.Coche;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Coche coche = DaggerComponent.create().build();
        coche.getPasajero();
        coche.getSilla();
    }
}