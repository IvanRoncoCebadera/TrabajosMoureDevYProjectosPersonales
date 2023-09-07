package di.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import models.pasajero.Conductor;
import models.pasajero.NoConductor;
import models.pasajero.Pasajero;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class PasajeroModule {
    @Singleton
    @Provides
    @Named("Si")
    public Pasajero provideConductor(){
        return new Conductor();
    }

    @Singleton
    @Provides
    @Named("No")
    public Pasajero provideNoConductor(){
        return new NoConductor();
    }
}
