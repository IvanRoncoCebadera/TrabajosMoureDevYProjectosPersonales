package di.component;

import dagger.Component;
import di.modules.myModule;
import models.Persona;

import javax.inject.Singleton;

@Singleton
@Component(modules = {myModule.class})
public interface myComponent {
    Persona build();
}

