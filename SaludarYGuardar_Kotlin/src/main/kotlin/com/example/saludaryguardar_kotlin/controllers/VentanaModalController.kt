package com.example.saludaryguardar_kotlin.controllers

import com.example.saludaryguardar_kotlin.data.ShareStateData
import javafx.fxml.FXML
import javafx.scene.control.Label
import mu.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger {  }

class VentanaModalController {

    init {
        logger.debug { "Se inicia la interfaz gráfica de la ventana modal" }
        Locale.setDefault(Locale("es", "ES"))
    }

    @FXML
    private lateinit var nombreModal: Label
    @FXML
    private lateinit var edadModal: Label
    @FXML
    private lateinit var emailModal: Label

    @FXML
    private fun initialize(){
        nombreModal.text = "Tu nombre es: "+ShareStateData.nombre
        edadModal.text = "Tu edad es: "+ShareStateData.edad
        emailModal.text = "Tu email es: "+ShareStateData.email
    }
}