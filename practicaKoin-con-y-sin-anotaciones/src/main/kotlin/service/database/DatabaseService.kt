package service.database

import config.ConfigApp
import org.koin.core.annotation.Singleton

@Singleton
class DatabaseService(
    private val config: ConfigApp
)  {
}