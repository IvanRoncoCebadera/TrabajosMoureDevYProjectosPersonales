package models;

import org.jetbrains.annotations.NotNull;

public class SingletonData {
    public SingletonData() {}
    private static SingletonData instance = null;
    public static SingletonData getInstance(){
        if(instance == null) instance = new SingletonData();
        return instance;
    }
    @NotNull
    String datos;

    @NotNull
    public String getDatos() {
        return datos;
    }

    public void setDatos(@NotNull String datos) {
        this.datos = datos;
    }

    public void imprimirDatos(){
        System.out.println(datos);
    }
}
