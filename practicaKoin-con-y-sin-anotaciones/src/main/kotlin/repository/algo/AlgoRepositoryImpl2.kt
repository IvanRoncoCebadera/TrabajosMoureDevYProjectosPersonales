package service.storage.algo

import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import service.database.DatabaseService

@Singleton
@Named("R2")
class AlgoRepositoryImpl2(
    private val database: DatabaseService
): AlgoRepository {
}