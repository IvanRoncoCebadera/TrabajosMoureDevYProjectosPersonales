package StarWarsCloneWar;

import java.util.Scanner;

public class simulacionStarWarsCloneWar {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        int tamañoFilaColumna = 0;
        int numeroDroides = 0;
        int tiempoASimular = 0;

        if(args.length != 3 || !areArgumentsValid(args)){
            System.out.println("Los datos introducidos por argumentos son inválidos o inexistentes, por lo que deberas escribirlos:");
            System.out.println("Introduce el tamaño de la filas y columnas del espacio:");
            tamañoFilaColumna = introduceSpaceSize();
            System.out.println();
            System.out.println("Introduce el número de droides que tendrá el espacio:");
            numeroDroides = introduceNumberOfDroides();
            System.out.println();
            System.out.println("Introduce los segundos que tendremos que simular el juego:");
            tiempoASimular = introduceTimeToMakeTheSimulation()*1000;
            System.out.println();
        }else{
            System.out.println("Los datos introducidos por argumentos son válidos.");
            tamañoFilaColumna = Integer.parseInt(args[0]);
            numeroDroides = Integer.parseInt(args[1]);
            tiempoASimular = Integer.parseInt(args[2])*1000;
        }

        while(numeroDroides > (tamañoFilaColumna*tamañoFilaColumna)){
            System.out.println("El numero de droides no puede superar al número de casillas del espacio ("+(tamañoFilaColumna*tamañoFilaColumna)+"), vuelva a introducir un número de droides:");
            numeroDroides = introduceNumberOfDroides();
        }

        Espacio espacio = new Espacio(numeroDroides, tamañoFilaColumna, tiempoASimular);

        espacio.simulacion();
    }

    private static boolean areArgumentsValid(String[] args) {
        try {
            isSizeValid(args[0]);
            isNumberValid(args[1]);
            areSecondsValid(args[2]);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private static int introduceTimeToMakeTheSimulation() {
        String seconds = "";
        do{
            try{
                seconds = sc.nextLine().trim();
                areSecondsValid(seconds);
            }catch (Exception e){
                seconds = "";
                System.out.println(e.getMessage());
            }
        }while(seconds.equals(""));
        return Integer.parseInt(seconds);
    }

    private static void areSecondsValid(String seconds) {
        if(seconds.isEmpty()){
            throw new IllegalArgumentException("Error del sistema: Los segundos no pueden estar vacios.");
        }
        var regex = "-?[0-9]+";
        if(!seconds.matches(regex)){
            throw new IllegalArgumentException("Error del sistema: Los segundos introducidos, no son un número.");
        }
        int n = Integer.parseInt(seconds);
        if(n < 1 || n > 3){
            throw new IllegalArgumentException("Error del sistema: Los segundos introducidos, deben estar entre 1 y 3.");
        }
    }

    private static int introduceSpaceSize() {
        String size = "";
        do{
            try{
                size = sc.nextLine().trim();
                isSizeValid(size);
            }catch (Exception e){
                size = "";
                System.out.println(e.getMessage());
            }
        }while(size.equals(""));
        return Integer.parseInt(size);
    }

    private static void isSizeValid(String size) {
        if(size.isEmpty()){
            throw new IllegalArgumentException("Error del sistema: El tamaño de filas/columnas no puede estar vacio.");
        }
        var regex = "-?[0-9]+";
        if(!size.matches(regex)){
            throw new IllegalArgumentException("Error del sistema: El tamaño de filas/columnas introducido, no es un número.");
        }
        int n = Integer.parseInt(size);
        if(n < 5 || n > 9){
            throw new IllegalArgumentException("Error del sistema: El tamaño de filas/columnas introducido, debe estar entre 5 y 9.");
        }
    }

    private static int introduceNumberOfDroides() {
        String number = "";
        do{
            try{
                number = sc.nextLine().trim();
                isNumberValid(number);
            }catch (Exception e){
                number = "";
                System.out.println(e.getMessage());
            }
        }while(number.equals(""));
        return Integer.parseInt(number);
    }

    private static void isNumberValid(String number) {
        if(number.isEmpty()){
            throw new IllegalArgumentException("Error del sistema: El numero de droides no puede estar vacio.");
        }
        var regex = "-?[0-9]+";
        if(!number.matches(regex)){
            throw new IllegalArgumentException("Error del sistema: El numero de droides introducido, no es un número.");
        }
        int n = Integer.parseInt(number);
        if(n < 5 || n > 30){
            throw new IllegalArgumentException("Error del sistema: El numero de droides introducido, debe estar entre 5 y 30.");
        }
    }
}
