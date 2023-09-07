package com.example.saludaryguardar_kotlin.routes

import com.example.saludaryguardar_kotlin.HelloApplication
import com.example.saludaryguardar_kotlin.controllers.VentanaPrincipalController
import com.example.saludaryguardar_kotlin.utils.getResource
import com.example.saludaryguardar_kotlin.utils.getResourceAsStream
import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.stage.Modality
import javafx.stage.Stage
import mu.KotlinLogging
import java.io.InputStream
import java.net.URL
import java.time.LocalTime
import java.util.*
import java.util.concurrent.ThreadFactory
import kotlin.collections.HashMap

private val logger = KotlinLogging.logger {  }

object RoutesManager {
    private lateinit var mainStage: Stage
    private lateinit var _activeStage: Stage
    private val activeStage get() = _activeStage
    lateinit var app: Application

    init {
        logger.debug { "Se inicia el RoutesManager" }
    }

    enum class views(val url: String){
        MAIN("views/ventana-principal.fxml"),
        MODAL("views/ventana-modal.fxml")
    }

    fun initMainStage(stage: Stage){
        val fxmlLoader = FXMLLoader(getResource(views.MAIN.url))
        val scene = Scene(fxmlLoader.load(), 350.0, 250.0)
        stage.title = "Presentación alumno/a"
        stage.isResizable = false
        stage.icons.add(Image(getResourceAsStream("icons/icono.png")))
        stage.setOnCloseRequest {
            if(fxmlLoader.getController<VentanaPrincipalController>().onCloseAction()){
                Platform.exit()
            }else{
                it.consume()
            }
        }
        stage.scene = scene

        mainStage = stage
        _activeStage = stage
        activeStage.show()
    }

    fun initModalStage(){
        val fxmlLoader = FXMLLoader(getResource(views.MODAL.url))
        val scene = Scene(fxmlLoader.load(), 300.0, 175.0)
        val stage = Stage()
        stage.title = "Datos personales y del alumno"
        stage.isResizable = false
        stage.icons.add(Image(getResourceAsStream("icons/icono.png")))
        stage.scene = scene

        stage.initOwner(mainStage)
        stage.initModality(Modality.WINDOW_MODAL)

        _activeStage = stage
        activeStage.show()
    }
}