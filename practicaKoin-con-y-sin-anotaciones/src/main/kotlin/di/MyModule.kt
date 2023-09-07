package di

import config.ConfigApp
import org.koin.core.qualifier.named
import org.koin.dsl.module
import service.database.DatabaseService
import service.storage.algo.*
import viewmodel.Viewmodel

val myModule = module {
    single { ConfigApp() }

    single { DatabaseService(get()) }

    single<AlgoStorage>(named("S1")){ AlgoStorageImpl(get()) }
    single<AlgoStorage>(named("S2")){ AlgoStorageImpl2(get()) }

    single<AlgoRepository>(named("R1")){ AlgoRepositoryImpl(get()) }
    single<AlgoRepository>(named("R2")){ AlgoRepositoryImpl2(get()) }

    single { Viewmodel(get(named("R1")), get(named("S2"))) }
}