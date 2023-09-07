package service.storage.algo

import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import service.database.DatabaseService

@Singleton
@Named("R1")
class AlgoRepositoryImpl(
    private val database: DatabaseService
): AlgoRepository  {
}