package controller;

import models.Trabajador;
import repository.ITrabajadorRepo;
import service.storage.trabajador.ITrabajadorStorage;

import javax.inject.Inject;
import javax.inject.Named;

public class Controller {
    private ITrabajadorRepo<Trabajador, String> repo;
    private ITrabajadorStorage storage;

    @Inject
    public Controller(
            @Named("1")
            ITrabajadorRepo<Trabajador, String> repo,
            ITrabajadorStorage storage
    ) {
        this.repo = repo;
        this.storage = storage;
    }
}
