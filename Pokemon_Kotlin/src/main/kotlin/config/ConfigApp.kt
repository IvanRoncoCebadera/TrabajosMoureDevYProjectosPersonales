package config

import java.io.File
import java.nio.file.Files
import java.util.*

object ConfigApp {

    lateinit var APP_DATA: String;
    lateinit var APP_AUTHOR: String;
    lateinit var APP_VERSION: String;
    lateinit var APP_NAME: String;
    val myPath = System.getProperty("user.dir")+ File.separator

    init {
        loadProperties()
        initStorage()
    }

    private fun loadProperties() {
        val property = Properties()
        property.load(ConfigApp::class.java.getResourceAsStream("/config.properties"))
        APP_NAME = property.getProperty("APP_NAME") ?: "Pokemon_Kotlin"
        APP_VERSION = property.getProperty("APP_VERSION") ?: "1.0.0"
        APP_AUTHOR = property.getProperty("APP_AUTHOR") ?: "IvanRoncoCebadera"
        APP_DATA = property.getProperty("APP_DATA") ?: "data"
        APP_DATA = myPath+ APP_DATA
    }

    private fun initStorage() {
        val file = File(APP_DATA)
        if(!file.exists()){
            Files.createDirectory(file.toPath())
        }
    }
}