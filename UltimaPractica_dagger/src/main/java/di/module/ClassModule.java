package di.module;

import config.ConfigApp;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import service.database.DatabaseManager;

import javax.inject.Singleton;

@Module
public class ClassModule {
    @Singleton
    @Provides
    public ConfigApp bindConfigApp(){
        return new ConfigApp();
    }

    @Singleton
    @Provides
    public DatabaseManager bindModule(){
        return new DatabaseManager(bindConfigApp());
    }
}
