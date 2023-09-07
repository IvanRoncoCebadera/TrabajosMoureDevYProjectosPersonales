package di.module;

import config.ConfigApp;
import dagger.Binds;
import dagger.Provides;
import models.Trabajador;
import repository.ITrabajadorRepo;
import repository.TrabajadorRepoImpl1;
import repository.TrabajadorRepoImpl2;
import service.database.DatabaseManager;
import service.storage.trabajador.ITrabajadorStorage;
import service.storage.trabajador.TrabajadorStorageImpl;

import javax.inject.Named;
import javax.inject.Singleton;

@dagger.Module
public interface Module {
    @Singleton
    @Binds
    ITrabajadorStorage bindTrabajadorStorage(TrabajadorStorageImpl impl);

    @Singleton
    @Binds
    @Named("1")
    ITrabajadorRepo<Trabajador, String> bindTrabajador1(TrabajadorRepoImpl1 impl);

    @Singleton
    @Binds
    @Named("2")
    ITrabajadorRepo<Trabajador, String> bindTrabajador2(TrabajadorRepoImpl2 impl);
}
