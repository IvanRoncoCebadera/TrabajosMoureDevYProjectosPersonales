package repository;

import models.Trabajador;
import service.database.DatabaseManager;

import javax.inject.Inject;

public class TrabajadorRepoImpl1 implements ITrabajadorRepo<Trabajador, String> {
    private DatabaseManager databaseManager;
    @Inject
    public TrabajadorRepoImpl1(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}
