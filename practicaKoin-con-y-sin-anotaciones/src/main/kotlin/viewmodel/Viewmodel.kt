package viewmodel

import org.koin.core.annotation.Named
import org.koin.core.annotation.Singleton
import service.storage.algo.AlgoRepository
import service.storage.algo.AlgoStorage

@Singleton
class Viewmodel(
    //Esto es por koin annotation
    @Named("R2")
    private val repository: AlgoRepository,
    //Esto es por koin annotation
    @Named("S1")
    private val storage: AlgoStorage
) {
}