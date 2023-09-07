package models

import com.squareup.moshi.Json
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Json(name = "etapa")
@Root(name = "etapa")
data class Etapa(
    @Json(name = "num")
    @field:Element(name = "num")
    @param:Element(name = "num")
    val num: Int,

    @Json(name = "name")
    @field:Element(name = "name")
    @param:Element(name = "name")
    val name: String
): Serializable