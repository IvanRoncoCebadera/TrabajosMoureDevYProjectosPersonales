package service.storage.trabajador;

import config.ConfigApp;

import javax.inject.Inject;

public class TrabajadorStorageImpl implements ITrabajadorStorage {
    private ConfigApp configApp;
    @Inject
    public TrabajadorStorageImpl(ConfigApp configApp) {
        this.configApp = configApp;
    }
}
