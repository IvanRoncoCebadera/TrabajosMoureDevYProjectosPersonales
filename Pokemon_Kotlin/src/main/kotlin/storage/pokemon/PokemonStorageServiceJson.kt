package storage.pokemon

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.ConfigApp
import models.Pokedex
import models.Pokemon
import java.io.File
import java.io.IOException

@ExperimentalStdlibApi
class PokemonStorageServiceJson: PokemonStorageService {

    val configApp = ConfigApp
    val file = File(configApp.APP_DATA+File.separator+"pokemon.json")
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter<Pokedex>()

    override fun loadAll(): List<Pokemon> {
        if(!file.exists()) return emptyList()
        return jsonAdapter.fromJson(file.readText())!!.pokedex
    }

    override fun saveAll(entites: List<Pokemon>) {
        file.writeText(jsonAdapter.indent("   ").toJson(Pokedex(entites)))
    }

    fun loadAllFromProperties(): List<Pokemon>{
        val path = ClassLoader.getSystemResource("pokemon.json").file ?: throw IOException("Fichero no encontrado.")
        val filePkm = File(path)
        val pokemons: List<Pokemon> = emptyList()
        if(!filePkm.exists()) return pokemons
        return jsonAdapter.fromJson(filePkm.readText())!!.pokedex
    }
}