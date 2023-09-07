package org.example;

import java.util.Scanner;

public class juegoNumerosBinariosStarWars {

    public static final Scanner sc = new Scanner(System.in);
    public static final int C3P0 = 0;
    public static final int R2D2 = 1;
    public static final int BB8 = 2;

    public static void main(String[] args){
        String binarios = "";
        String[] cantidades0 = new String[3];
        int[] victorias = new int[3];

        victorias = jugar(binarios, cantidades0, victorias);
        System.out.println("Termino el juego, la tabla de calificaciones quedo de la siguiente manera:");
        String[] calificaciones = new String[3];
        calificaciones = oredenarCalificaciones(victorias);
        leer(calificaciones);
    }

    private static String[] oredenarCalificaciones(int[] victorias) {
        String[] calificaciones = new String[3];
        int posicion = 0;
        int numero_victorias = 0;
        for(int j = 0; j < victorias.length; j++) {
            numero_victorias = 0;
            for (int i = 0; i < victorias.length; i++) {
                if (victorias[i] >= numero_victorias) {
                    numero_victorias = victorias[i];
                    posicion = i;
                }
            }
            victorias[posicion] = -1;
            switch(posicion){
                case C3P0 -> calificaciones[posicion] = "C3P0";
                case R2D2 -> calificaciones[posicion] = "R2D2";
                case BB8 -> calificaciones[posicion] = "BB8";
            }
        }
        return calificaciones;
    }

    private static int[] jugar(String binarios, String[] cantidades0, int[] victorias) {
        do {
            binarios = binarios + escribirBinarioC3P0();
            binarios = binarios + escribirBinarioAleatorio();
            binarios = binarios + escribirBinarioAleatorio();

            cantidades0 = ingresarCantidadDeCeros(cantidades0);

            victorias = calcularVencedor(binarios, cantidades0, victorias);

            System.out.println(binarios);
            leer(cantidades0);

            cantidades0[C3P0] = "0";
            cantidades0[R2D2] = "0";
            cantidades0[BB8] = "0";
            binarios = "";
        }while(victorias[C3P0] != 3 && victorias[R2D2] != 3 && victorias[BB8] != 3);
        return victorias;
    }

    private static void leer(String[] cantidades0) {
        String mensaje = "";
        for(int i = 0; i< cantidades0.length; i++){
            if(i == 0) {
                mensaje = mensaje + cantidades0[i];
            }else {
                mensaje = mensaje + ", " + cantidades0[i];
            }
        }
        System.out.println(mensaje);
    }

    private static int[] calcularVencedor(String binario, String[] cantidades0, int[] victorias) {
        String cantidad0 = calcularNumero0s(binario);
        System.out.println("El numero total de ceros era: " + cantidad0 + ", por ende:");
        if(cantidades0[C3P0].equals(cantidad0)){
            System.out.println("C3P0 gano.");
            victorias[C3P0] ++;
        }else{
            if(cantidades0[R2D2].equals(cantidad0)){
                System.out.println("R2D2 gano.");
                victorias[R2D2] ++;
            }else{
                if(cantidades0[BB8].equals(cantidad0)){
                    System.out.println("BB8 gano.");
                    victorias[BB8] ++;
                }else{
                    System.out.println("Nadie gano.");
                }
            }
        }
        return victorias;
    }

    private static String calcularNumero0s(String binario) {
        int cont = 0;
        for(int i = 0; i < binario.length(); i++){
            char c = binario.charAt(i);
            if(c == '0'){
                cont++;
            }
        }
        String contador = ""+cont;
        return contador;
    }

    private static String[] ingresarCantidadDeCeros(String[] cantidades0) {
        cantidades0[R2D2] = escribirCantidadAleatoria(cantidades0);
        cantidades0[BB8] = escribirCantidadAleatoria(cantidades0);
        cantidades0[C3P0] = escribirCantidad(cantidades0);
        return cantidades0;
    }

    private static String escribirCantidad(String[] cantidades0) {
        String c3p0 = "";
        var regex = "[1-9]";
        System.out.println("C3P0, es tu turno de elegir una cantidad de ceros en total entre todos los numeros binarios (debe ser entre 1 y 9, ademas no puedes repetir numero con respecto a R2D2 y BB8):");
        do {
            c3p0 = sc.nextLine();
            if(!c3p0.matches(regex) || c3p0.equals(cantidades0[R2D2]) || c3p0.equals(cantidades0[BB8])){
                System.out.println("Ese numero no es válido C3P0 o ya está cogido, vuelve a probar:");
            }
        }while(!c3p0.matches(regex) || c3p0.equals(cantidades0[R2D2]) || c3p0.equals(cantidades0[BB8]));
        return c3p0;
    }

    private static String escribirCantidadAleatoria(String[] cantidades0) {
        String cant = "";
        do {
            switch ((int) (Math.random() * 10)) {
                case 1 -> cant = "1";
                case 2 -> cant = "2";
                case 3 -> cant = "3";
                case 4 -> cant = "4";
                case 5 -> cant = "5";
                case 6 -> cant = "6";
                case 7 -> cant = "7";
                case 8 -> cant = "8";
                case 9 -> cant = "9";
                case 10 -> cant = "-1";
            }
        }while(cant == "-1" || cant.equals(cantidades0[R2D2]) || cant.equals(cantidades0[BB8]));
        return cant;
    }

    private static String escribirBinarioAleatorio() {
        String androide = "";
        var regex = "[0-1]{3}";
        do {
            androide = androide + binarioAleatorio();
        }while(!androide.matches(regex));
        return androide;
    }

    private static String binarioAleatorio() {
        int numero = (int) (Math.random() * 10);
        if(numero < 5) {
            return "0";
        }
        return "1";
    }

    private static String escribirBinarioC3P0() {
        String c3p0 = "";
        var regex = "[0-1]{3}";
        System.out.println("C3P0, es tu turno de elegir un número binario de tres dígitos:");
        do {
            c3p0 = sc.nextLine();
            if(!c3p0.matches(regex)){
                System.out.println("Ese numero no es válido C3P0, vuelve a probar:");
            }
        }while(!c3p0.matches(regex));
        return c3p0;
    }
}
