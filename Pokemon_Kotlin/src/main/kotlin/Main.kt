import models.Pokemon
import mu.KotlinLogging
import storage.pokemon.*
import java.util.logging.Logger

@OptIn(ExperimentalStdlibApi::class)
fun main(args: Array<String>) {

    var pokemones: List<Pokemon> = emptyList()

    val json = PokemonStorageServiceJson()

    pokemones = json.loadAllFromProperties()

    json.saveAll(pokemones)

    json.loadAll()

    val xml = PokemonStorageServiceXml()

    // xml.saveAll(pokemones)

    val ser = PokemonStorageServiceSer()

    ser.saveAll(pokemones)

    val txt = PokemonStorageServiceTexto()

    txt.saveAll(pokemones)

    val bin = PokemonStorageServiceBin()

    bin.saveAll(pokemones)
}