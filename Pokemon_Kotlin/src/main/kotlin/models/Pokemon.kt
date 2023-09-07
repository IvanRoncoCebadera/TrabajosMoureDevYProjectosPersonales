package models

import com.squareup.moshi.Json
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable

data class Pokemon(
    @Json(name = "id")
    @field:Element(name = "id")
    @param:Element(name = "id")
    val id: Int,

    @Json(name = "num")
    @field:Element(name = "num")
    @param:Element(name = "num")
    val num: String,

    @Json(name = "name")
    @field:Element(name = "name")
    @param:Element(name = "name")
    val name: String,

    @Json(name = "img")
    @field:Element(name = "img")
    @param:Element(name = "img")
    val img: String,

    @Json(name = "type")
    @field:ElementList(name = "type", inline = true)
    @param:ElementList(name = "type", inline = true)
    val type: List<String>,

    @Json(name = "height")
    @field:Element(name = "height")
    @param:Element(name = "height")
    val height: String,

    @Json(name = "weight")
    @field:Element(name = "weight")
    @param:Element(name = "weight")
    val weight: String,

    @Json(name = "candy")
    @field:Element(name = "candy")
    @param:Element(name = "candy")
    val candy: String,

    @Json(name = "candy_count")
    @field:Element(name = "candy_count", required = false)
    @param:Element(name = "candy_count", required = false)
    val candyCount: Int?,

    @Json(name = "egg")
    @field:Element(name = "egg")
    @param:Element(name = "egg")
    val egg: String,

    @Json(name = "spawn_chance")
    @field:Element(name = "spawn_chance")
    @param:Element(name = "spawn_chance")
    val spawnChance: Double,

    @Json(name = "avg_spawns")
    @field:Element(name = "avg_spawns")
    @param:Element(name = "avg_spawns")
    val avgSpawn: Double,

    @Json(name = "spawn_time")
    @field:Element(name = "spawn_time")
    @param:Element(name = "spawn_time")
    val spawnTime: String,

    @Json(name = "multipliers")
    @field:ElementList(name = "multipliers", inline = true)
    @param:ElementList(name = "multipliers", inline = true)
    val multipliers: List<Double>?,

    @Json(name = "weaknesses")
    @field:ElementList(name = "weaknesses", inline = true)
    @param:ElementList(name = "weaknesses", inline = true)
    val weaknesses: List<String>,

    @Json(name = "next_evolution")
    @field:ElementList(name = "next_evolution", inline = true, required = false)
    @param:ElementList(name = "next_evolution", inline = true, required = false)
    val nextEvolution: List<Etapa>?,

    @Json(name = "prev_evolution")
    @field:ElementList(name = "prev_evolution", inline = true, required = false)
    @param:ElementList(name = "prev_evolution", inline = true, required = false)
    val prevEvolution: List<Etapa>?
): Serializable