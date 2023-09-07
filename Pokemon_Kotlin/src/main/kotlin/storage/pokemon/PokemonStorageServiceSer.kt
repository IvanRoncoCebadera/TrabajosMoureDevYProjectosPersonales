package storage.pokemon

import config.ConfigApp
import models.Pokedex
import models.Pokemon
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class PokemonStorageServiceSer: PokemonStorageService {

    val configApp = ConfigApp
    val file = File(configApp.APP_DATA+ File.separator+"pokemon.ser")

    override fun loadAll(): List<Pokemon> {
        if(!file.exists()) return emptyList()
        return ObjectInputStream(FileInputStream(file)).use {
            (it.readObject() as Pokedex).pokedex
        }
    }

    override fun saveAll(entites: List<Pokemon>) {
        ObjectOutputStream(FileOutputStream(file)).use {
            it.writeObject(Pokedex(entites))
        }
    }
}