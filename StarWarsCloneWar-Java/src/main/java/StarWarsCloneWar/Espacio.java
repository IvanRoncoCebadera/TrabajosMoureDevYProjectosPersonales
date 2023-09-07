package StarWarsCloneWar;

public class Espacio {

    final int INCREMENTO_TIEMPO = 100;

    int contadorTiros = 0;
    int contadorTirosAcertados = 0;
    int contadorTirosCriticos = 0;
    int tiempoActualDeSimulacion = 0;
    int numeroDroides;
    int tamañoFilaColumna;
    int tiempoASimular;

    public Espacio(int numeroDroides, int tamañoFilaColumna, int tiempoASimular) {
        this.numeroDroides = numeroDroides;
        this.tamañoFilaColumna = tamañoFilaColumna;
        this.tiempoASimular = tiempoASimular;
    }

    Droide[][] espacio;

    Droide[] arrayDroidesMuertos;

    public void simulacion() throws InterruptedException {
        inicializeSpace();
        System.out.println("El espacio inicial con los droides es el siguiente:");
        showSpace();
        System.out.println();
        do{
            //Represento el tiempo que va pasando en la simulación, con un String.format
            System.out.println("El tiempo actual es: "+String.format("%.1f",(tiempoActualDeSimulacion/1000.0)));
            System.out.println();

            if(tiempoActualDeSimulacion != 0 && tiempoActualDeSimulacion % 300 == 0){
                moveAllDroides();
                System.out.println("Los droides se han recolocago, y ahora está de la siguiente manera:");
                showSpace();
                System.out.println();
            }else{
                if(tiempoActualDeSimulacion != 0) {
                    System.out.println("Por ahora el espacio es el siguiente:");
                    showSpace();
                    System.out.println();
                }
            }

            int[] coordenates = getCoordenatesToShoot();
            shootDroide(coordenates[0], coordenates[1]);
            contadorTiros++;
            removeAnyDroideWith0Health();
            tiempoActualDeSimulacion += wait100Miliseconds();
            System.out.println();

        }while(tiempoActualDeSimulacion <= tiempoASimular && thereArStillEnemies());
        System.out.println("Termino el juego, los resultados son los siguientes:");
        showRecordOfSimulation();
    }

    private void showRecordOfSimulation() {
        int droidesDestruidos = firstNullPosition();
        System.out.println("Al principio había un total de: "+numeroDroides+" droides");
        System.out.println("Al final, ha quedado un total de: "+(numeroDroides-droidesDestruidos)+" droides" +
                ", por lo que se han destruido un total de: "+droidesDestruidos+" droides");
        System.out.println("El porcentaje de aciertos es: "+String.format("%.2f", (double)(contadorTirosAcertados*100/contadorTiros))+"%");
        System.out.println("Se han realizado un total de : "+contadorTiros+" tiros" +
                ", de los cuales, se acertaron "+contadorTirosAcertados+"" +
                ", y de esos, "+contadorTirosCriticos+" fueron críticos.");
        getAllDroidesIntoArray();
        orderDroidesByEnergyLeft();
        showDroides();
    }

    private void showDroides() {
        for(int i=0;i< arrayDroidesMuertos.length;i++) {
            System.out.println(arrayDroidesMuertos[i]);
        }
    }

    private void orderDroidesByEnergyLeft() {
        for(int i=0;i< arrayDroidesMuertos.length;i++) {
            for (int j = 0; j < arrayDroidesMuertos.length-i; j++) {
                if(j < arrayDroidesMuertos.length && j >= 0 && j+1 < arrayDroidesMuertos.length && j+1 >= 0){
                    if(arrayDroidesMuertos[j].energia < arrayDroidesMuertos[j+1].energia){
                        Droide auxiliar = arrayDroidesMuertos[j];
                        arrayDroidesMuertos[j] = arrayDroidesMuertos[j+1];
                        arrayDroidesMuertos[j+1] = auxiliar;
                    }
                }
            }
        }
    }

    private void getAllDroidesIntoArray() {
        int contador = firstNullPosition();
        for(int i=0;i< espacio.length;i++) {
            for (int j = 0; j < espacio[i].length; j++) {
                if(espacio[i][j] != null){
                    arrayDroidesMuertos[contador] = espacio[i][j];
                    contador++;
                }
            }
        }
    }

    private int wait100Miliseconds() throws InterruptedException {
        Thread.sleep(INCREMENTO_TIEMPO);
        return INCREMENTO_TIEMPO;
    }

    private void removeAnyDroideWith0Health() {
        for(int i=0;i< espacio.length;i++) {
            for (int j = 0; j < espacio[i].length; j++) {
                if(espacio[i][j] != null && espacio[i][j].energia == 0){
                    Droide droideAEliminar = espacio[i][j];
                    System.out.println("Como al droide: "+droideAEliminar+", no le quedan puntos de vida, será retirado del espacio.");
                    arrayDroidesMuertos[firstNullPosition()] = droideAEliminar;
                    espacio[i][j] = null;
                }
            }
        }
    }

    private void shootDroide(int fila, int columna) {
        Droide droideDisparado = espacio[fila][columna];
        System.out.println("Has disparado a la posicion: "+(fila+1)+"-"+(columna+1)+", donde estaba el droide: "+droideDisparado);
        int chance = (int)(Math.random()*100);
        if(chance <= 90){
            contadorTirosAcertados++;
            System.out.println("Con un poco de suerte, acertaste el tiro");
            if(chance <= 15){
                contadorTirosCriticos++;
                droideDisparado.energia -= 5;
                //Nos aseguramos de que no pueda tener una cantidad de vida negativa
                if(droideDisparado.energia < 0){
                    droideDisparado.energia = 0;
                }
                System.out.println("Ademas, fue un tiro crítico, por lo que le hiciste 5 de daño, ahora sus escudos están a: "+droideDisparado);
            }else{
                droideDisparado.energia -= 1;
                System.out.println("Fue un tiro normal, por lo que le hiciste 1 de daño, ahora sus escudos están a: "+droideDisparado);
            }
        }else{
            System.out.println("Pero por desgracia, has fallado el tiro.");
        }
    }

    private int[] getCoordenatesToShoot() {
        int[] coordenates = new int[2];
        int fila;
        int columna;
        do {
            fila = (int) (Math.random() * tamañoFilaColumna);
            columna = (int) (Math.random() * tamañoFilaColumna);
        }while(espacio[fila][columna] == null);
        coordenates[0] = fila;
        coordenates[1] = columna;
        return coordenates;
    }

    private void moveAllDroides() {
        Droide[][] doubleBuffer = new Droide[tamañoFilaColumna][tamañoFilaColumna];
        for(int i=0;i< espacio.length;i++) {
            for (int j = 0; j < espacio[i].length; j++) {
                if(espacio[i][j] != null){
                    moveDroide(espacio[i][j], doubleBuffer);
                }
            }
        }
        espacio = doubleBuffer;
    }

    private void moveDroide(Droide droide, Droide[][] doubleBuffer) {
        int fila = (int) (Math.random() * tamañoFilaColumna);
        int columna= (int) (Math.random() * tamañoFilaColumna);
        Droide d = doubleBuffer[fila][columna];
        doubleBuffer[fila][columna] = droide;
        if(d != null){
            moveDroide(d, doubleBuffer);
        }
    }

    private boolean thereArStillEnemies() {
        for(int i=0;i< espacio.length;i++) {
            for (int j = 0; j < espacio[i].length; j++) {
                if(espacio[i][j] != null) return true;
            }
        }
        return false;
    }

    public void inicializeSpace(){
        espacio = new Droide[tamañoFilaColumna][tamañoFilaColumna];
        arrayDroidesMuertos = new Droide[numeroDroides];
        int fila;
        int columna;
        for(int i=0;i<numeroDroides;i++){
            do {
                fila = (int) (Math.random() * tamañoFilaColumna);
                columna = (int) (Math.random() * tamañoFilaColumna);
            }while(espacio[fila][columna] != null);
            espacio[fila][columna] = FactoryDroide.getInstance().createRandomly();
        }
    }

    public void showSpace(){
        String mensaje = "";
        for(int i=0;i< espacio.length;i++){
            for(int j=0;j< espacio[i].length;j++){
                // En esta parte, si en vez de herencia, podria haber usado enums y añadido un tipo al droide, de manera que esto se simplificaba
                if(espacio[i][j] instanceof Droide.SW348){
                    mensaje += "  SW348 ";
                }else{
                    if(espacio[i][j] instanceof Droide.SW497){
                        mensaje += "  SW497 ";
                    }else{
                        if(espacio[i][j] instanceof Droide.SW4421){
                            mensaje += "  SW4421";
                        }else{
                            mensaje += "        ";
                        }
                    }
                }
            }
            System.out.println(mensaje);
            mensaje = "";
        }
    }

    private int firstNullPosition() {
        int posicion = -1;
        for(int i=0;i<arrayDroidesMuertos.length;i++){
            if(arrayDroidesMuertos[i] == null){
                posicion = i;
                break;
            }
        }
        return posicion;
    }
}
