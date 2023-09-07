package models

import com.squareup.moshi.Json
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.Serializer
import java.io.Serializable

@Json(name = "pokedex")
@Root(name = "pokedex")
data class Pokedex(
    @Json(name = "pokemon")
    @field:ElementList(name = "pokemon", inline = true)
    @param:ElementList(name = "pokemon", inline = true)
     val pokedex: List<Pokemon>
): Serializable