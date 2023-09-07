package di.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import models.silla.Silla;

import javax.inject.Singleton;

@Module
public class SillaModule {
    @Singleton
    @Provides
    public Silla providesSilla(){
        return new Silla();
    }
}
