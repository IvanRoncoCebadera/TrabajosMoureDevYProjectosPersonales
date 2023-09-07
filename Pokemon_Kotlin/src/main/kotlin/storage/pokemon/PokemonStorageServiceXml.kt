package storage.pokemon

import config.ConfigApp
import models.Pokedex
import models.Pokemon
import org.simpleframework.xml.core.Persister
import java.io.File

class PokemonStorageServiceXml: PokemonStorageService {

    val configApp = ConfigApp
    val file = File(configApp.APP_DATA+ File.separator+"pokemon.xml")
    val persister = Persister()

    override fun loadAll(): List<Pokemon> {
        if(!file.exists()) return emptyList()
        return persister.read(Pokedex::class.java, file).pokedex
    }

    override fun saveAll(entites: List<Pokemon>) {
        persister.write(Pokedex(entites), file)
    }
}