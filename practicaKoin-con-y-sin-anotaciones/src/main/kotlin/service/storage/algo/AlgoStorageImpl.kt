package service.storage.algo

import config.ConfigApp
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton

@Singleton
@Named("S1")
class AlgoStorageImpl(
    private val config: ConfigApp
) : AlgoStorage {
}