package org.example;

import controller.Controller;
import di.component.DaggerControllerComponent;

public class Main {
    public static void main(String[] args) {

        Controller controller = DaggerControllerComponent.create().build();

        System.out.println("Hello world!");
    }
}