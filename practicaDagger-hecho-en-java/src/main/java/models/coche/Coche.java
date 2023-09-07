package models.coche;

import models.pasajero.Pasajero;
import models.silla.Silla;

import javax.inject.Inject;
import javax.inject.Named;

public class Coche {

    Pasajero pasajero;

    Silla silla;

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Silla getSilla() {
        return silla;
    }

    @Inject
    public Coche(@Named("Si")Pasajero pasajero, Silla silla) {
        this.pasajero = pasajero;
        this.silla = silla;
    }
}
