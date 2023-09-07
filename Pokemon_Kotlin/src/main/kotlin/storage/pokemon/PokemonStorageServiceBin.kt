package storage.pokemon

import config.ConfigApp
import models.Pokemon
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class PokemonStorageServiceBin: PokemonStorageService {

    val configApp = ConfigApp
    val file = File(configApp.APP_DATA+ File.separator+"pokemon.bin")

    override fun loadAll(): List<Pokemon> {
        TODO("Not yet implemented")
    }

    override fun saveAll(entites: List<Pokemon>) {
        entites.forEach {pkm ->
            file.appendBytes((pkm.id.toString()+"\n").toByteArray())
            file.appendBytes((pkm.num+"\n").toByteArray())
            file.appendBytes((pkm.name+"\n").toByteArray())
        }
    }
}