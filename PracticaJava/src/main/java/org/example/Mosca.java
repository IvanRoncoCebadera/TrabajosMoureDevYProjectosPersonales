package org.example;

import java.util.Scanner;

public class Mosca {
    private static final String MOSCA = "1";
    private static final int TAMAÑO = 5;
    private static Scanner sc = new Scanner(System.in);


     public static void main(String[] args){
         String[][] mosca = new String[TAMAÑO][TAMAÑO];

         mosca = colocarMosca(mosca);

         mostrarMatriz(mosca);

         jugar(mosca);
     }

    private static void jugar(String[][] mosca) {
        int caso = 0;
         do {
             //String coordenada = escribirCoordenadas();

             int[] coordenadas = ingresarCoordenadas();

             caso = analizarGolpe(mosca, coordenadas);

             mosca = consecuenciasGolpe(caso, mosca);
         }while(caso != 1);
        System.out.println("Felicidades, has ganado, le diste a la mosca de pleno, GG, EZ, FACILTO.... Bueno ya paro.");
    }

    private static String[][] consecuenciasGolpe(int caso, String[][] mosca) {
         String[][] matriz = mosca;
         if(caso == 2){
             System.out.println("No has estado ni cerca de golpear a la mosca por lo que ni se ha movido.");
         }else{
             if(caso == 3){
                 System.out.println("Has estado tan cerca de golpear a la mosca que se a recolocado.");
                 matriz = colocarMosca(mosca);
             }
         }
         return matriz;
    }

    private static int[] ingresarCoordenadas() {
        int[] coordenadas = new int[2];
        do {
            System.out.println("Introduzca la coordenada de la fila, debe estar entre 1 y " + TAMAÑO);
            coordenadas[0] = sc.nextInt() - 1;
        }while(coordenadas[0] > 4 || coordenadas[0] < 0);
        do {
            System.out.println("Introduzca la coordenada de la columna, debe estar entre 1 y " + TAMAÑO);
            coordenadas[1] = sc.nextInt() - 1;
        }while(coordenadas[1] > 4 || coordenadas[1] < 0);
        return coordenadas;
    }

    private static int analizarGolpe(String[][] mosca, int[] coordenadas) {
         int caso = 0;
         if(mosca[coordenadas[0]][coordenadas[1]] == MOSCA){
             caso = 1;
         }else{
             for(int i = -1; i <= 1; i++){
                 for(int j = -1; j <= 1; j++){
                     if(coordenadas[0] + i < mosca.length && coordenadas[0] + i >= 0 && coordenadas[1] + j < mosca.length && coordenadas[1] + j >= 0){
                         if(mosca[coordenadas[0] + i][coordenadas[1] + j] == MOSCA){
                             return 3;
                         }else{
                             caso = 2;
                         }
                     }
                 }
             }
         }
         return caso;
    }

    private static String escribirCoordenadas() {
        final var regex = "[1-5]-[1-5]";
        String coordenadas = "";
        do {
            System.out.println("Por favor, usuario coloque las coordenadas de su golpe con el patron F-C, tenga en cuenta que la matriz tiene un tamaño de " + TAMAÑO + "X" + TAMAÑO + ":");
            coordenadas = sc.nextLine();
            if (!coordenadas.matches(regex)) {
                System.out.println("Así no sirven las coordenadas, vuelva a introducirlas:");
            }
        }while(!coordenadas.matches(regex));
        return coordenadas;
    }

    private static void mostrarMatriz(String[][] matriz) {
         String mensaje = "";
        for(int i = 0; i <= matriz.length - 1; i++){
            for(int j = 0; j < matriz[i].length; j++){
                mensaje = mensaje + matriz[i][j];
            }
            System.out.println(mensaje);
            mensaje = "";
        }
    }

    private static String[][] colocarMosca(String[][] mosca) {
         String[][] matriz = mosca;
         for(int i = 0; i < mosca.length; i++){
            for(int j = 0; j < mosca[i].length; j++){
                matriz[i][j] = "0";
            }
         }
         matriz[generarPosicionAleatoria()][generarPosicionAleatoria()] = MOSCA;
         return matriz;
    }

    private static int generarPosicionAleatoria() {
        int numero = (int) (Math.random() * 100);
        int posicion = 0;
        if(numero < 20){
            posicion = 0;
        }else{
            if(numero < 40){
                posicion = 1;
            }else{
                if(numero < 60){
                    posicion = 2;
                }else{
                    if(numero < 80){
                        posicion = 3;
                    }else{
                        posicion = 4;
                    }
                }
            }
        }
        return posicion;
    }
}
