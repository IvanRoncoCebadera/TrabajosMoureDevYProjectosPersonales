package repository;

import models.Trabajador;
import service.database.DatabaseManager;

import javax.inject.Inject;

public class TrabajadorRepoImpl2 implements ITrabajadorRepo<Trabajador, String> {
    private DatabaseManager databaseManager;
    @Inject
    public TrabajadorRepoImpl2(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}
