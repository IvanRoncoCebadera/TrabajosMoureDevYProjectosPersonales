package storage.pokemon

import config.ConfigApp
import models.Pokemon
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class PokemonStorageServiceTexto: PokemonStorageService {

    val configApp = ConfigApp
    val file = File(configApp.APP_DATA+ File.separator+"pokemon.txt")

    override fun loadAll(): List<Pokemon> {
        TODO()
    }

    override fun saveAll(entites: List<Pokemon>) {
        BufferedWriter(FileWriter(file)).use {
            entites.forEach { pkm ->
                it.appendLine(pkm.id.toString())
                it.appendLine(pkm.num)
                it.appendLine(pkm.name)
            }
        }
    }

}