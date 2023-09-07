import models.Data;
import models.Perro;
import utils.utilidades;

public class Main {
    public static void main(String[] args){
        System.out.println("Lo de Ktolin en Java:");

        System.out.println();

        Perro perro = new Perro(
                "Manuel",
                7,
                Perro.Raza.CANICHE,
                "HizoCacaSeka"
        );

        System.out.println(perro);

        utilidades.imprimirSaludoKotlin();

        utilidades.toSaludoFormal(perro.getNombre());

        Data.INSTANCE.setDatos("423");

        Data.imprimirDatos();
    }
}
