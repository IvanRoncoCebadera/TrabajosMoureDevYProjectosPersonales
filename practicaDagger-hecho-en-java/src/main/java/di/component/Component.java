package di.component;

import di.modules.PasajeroModule;
import di.modules.SillaModule;
import models.coche.Coche;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {SillaModule.class, PasajeroModule.class})
public interface Component {
    Coche build();
}
