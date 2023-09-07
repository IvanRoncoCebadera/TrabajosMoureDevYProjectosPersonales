package org.example;

import java.util.Scanner;

public class PrimerContactoJava {public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //leerMostrar();

        //ifElseSwitch();

        //operadorTernario();

        //buclesWhile();

        //buclesFor();


    }

    private static void buclesFor() {
        for(int i = 0; i <= 12; i+=4){
            System.out.println(i);
        }
        System.out.println();
        for(int i = 12; i >= 0; i-=4){
            System.out.println(i);
        }
        System.out.println();
        int[] vector = {1,2,3,4,5};
        for(int elemento: vector){
            System.out.println(elemento);
        }
        System.out.println();
        for(int i = 0; i < vector.length; i++){
            System.out.println("La posicion es: " + i);
            System.out.println("El valor es: " + vector[i]);
        }
        System.out.println();
        for(int i = vector.length - 1; i >= 0; i--){
            System.out.println("La posicion es: " + i);
            System.out.println("El valor es: " + vector[i]);
        }
        System.out.println();
        int[][] matriz = new int[3][3];
        for(int i = 0; i <= matriz.length - 1; i++){
            for(int j = 0; j < matriz[i].length; j++){
                matriz[i][j] = (int) (Math.random() * 10);
            }
        }
        String mensaje = "";
        StringBuilder builder = new StringBuilder();
        System.out.println("Sin StringBuilder:");
        for(int i = 0; i <= matriz.length - 1; i++){
            for(int j = 0; j < matriz[i].length; j++){
                mensaje = mensaje + matriz[i][j];
                if(i == 2 && j == 2){
                    builder.append(matriz[i][j]);
                }else {
                    builder.append(matriz[i][j] + ", ");
                }
            }
            System.out.println(mensaje);
            mensaje = "";
        }
        System.out.println("Con StringBuilder(): " + builder);
    }

    private static void buclesWhile() {
        int cont = 0;
        while(cont < 5){
            System.out.println(cont);
            cont++;
        }
        cont = 0;
        do{
            System.out.println(cont);
            cont++;
        }while(cont < 5);
    }

    private static void operadorTernario() {
        int a = 5;
        int b = 8;
        var res = (a > b) ? a : b;
        System.out.println("El mayor entre " + a + " y " + b + " es: " + res);
    }

    private static void ifElseSwitch() {
        String nombre = sc.nextLine();
        var regexPattern = "[a-zA-Z]+";
        int caso = 0;
        if(nombre.matches(regexPattern)){
            System.out.println("El nombre es válido, bien hecho.");
            caso = 1;
        }else{
            System.out.println("El nombre es invalido, WTF.");
            caso = 2;
        }
        switch (caso){
            case 1 -> System.out.println("El nombre es válido, bien hecho.");
            case 2 -> System.out.println("El nombre es invalido, WTF.");
        }
    }

    public static void leerMostrar(){
        System.out.println("Por favor, introduzca el mensaje que desee:");
        String mensaje = sc.nextLine();
        System.out.println(mensaje);
        String nombre = "";
        System.out.println("Por favor, introduzca su nombre, debe entender que solo puede utilizar letras:");
    }

}
