package service.database;

import config.ConfigApp;

import javax.inject.Inject;

public class DatabaseManager {
    private ConfigApp configApp;

    @Inject
    public DatabaseManager(ConfigApp configApp) {
        this.configApp = configApp;
    }
}
