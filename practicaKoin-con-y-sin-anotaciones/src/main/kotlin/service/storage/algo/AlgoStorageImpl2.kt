package service.storage.algo

import config.ConfigApp
import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton

@Singleton
@Named("S2")
class AlgoStorageImpl2(
    private val config: ConfigApp
) : AlgoStorage{
}