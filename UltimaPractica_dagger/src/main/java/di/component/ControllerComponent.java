package di.component;

import controller.Controller;
import dagger.Component;
import di.module.ClassModule;
import di.module.Module;

import javax.inject.Singleton;

@Singleton
@Component(modules = {Module.class, ClassModule.class})
public interface ControllerComponent {
    Controller build();
}
