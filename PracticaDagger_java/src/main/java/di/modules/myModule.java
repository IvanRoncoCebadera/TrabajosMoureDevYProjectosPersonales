package di.modules;

import dagger.Module;
import dagger.Provides;
import models.Trabajador;
import javax.inject.Singleton;

@Module
public class myModule {
    @Provides
    @Singleton
    Trabajador provideTrabajador(){
        return new Trabajador("Iván", 44444);
    }
}
