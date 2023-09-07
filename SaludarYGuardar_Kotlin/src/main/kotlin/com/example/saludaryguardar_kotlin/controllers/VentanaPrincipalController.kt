package com.example.saludaryguardar_kotlin.controllers

import com.example.saludaryguardar_kotlin.data.ShareStateData
import com.example.saludaryguardar_kotlin.repository.alumno.AlumnoRepositoyImpl
import com.example.saludaryguardar_kotlin.routes.RoutesManager
import com.example.saludaryguardar_kotlin.validator.validateAlumno
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.scene.control.TextField
import mu.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger {  }

class VentanaPrincipalController {

    private val repository = AlumnoRepositoyImpl()

    init {
        logger.debug { "Se inicia la interfaz gráfica de la ventana principal" }
        Locale.setDefault(Locale("es", "ES"))
        // var cont = 0
        // Esta es la primera vez que pruebo a tirar un segundo hilo
        // En el caso de este hilo, simulo un contador que llega hasta 60
        // Thread(Runnable {
        // println("Hola, segundo hilo!!!!!!!")
        // while (cont < 60){
        // Thread.sleep(1000)
        // cont++
        // }
        // println(cont)
        // Platform.exit()
        // }).start()
    }

    @FXML
    private lateinit var nombre: TextField
    @FXML
    private lateinit var edad: TextField
    @FXML
    private lateinit var email: TextField
    @FXML
    private lateinit var botonSaludar: Button
    @FXML
    private lateinit var botonLimpiar: Button
    @FXML
    private lateinit var botonListarAlumnos: Button
    @FXML
    private lateinit var botonInformacion: Button

    @FXML
    private fun initialize(){
        botonLimpiar.setOnAction { onCleanTextClick() }
        botonSaludar.setOnAction { onSaveClick() }
        botonListarAlumnos.setOnAction { onGetAlumnosClick() }
        botonInformacion.setOnAction { onBotonAbrirModalClick() }
    }

    fun onCloseAction(): Boolean{
        logger.debug { "Decidimos si realmente queremos salir o no" }
        val result = Alert(Alert.AlertType.CONFIRMATION).apply {
            title = "¿Quieres salir de la aplicación?"
            headerText = "¿Seguro que desea salir de la aplicación?"
            contentText = "Pulse cancelar para quedarse en la aplicación."
        }.showAndWait()
        return result.get() == ButtonType.OK
    }

    private fun onBotonAbrirModalClick() {
        logger.debug { "Abrimos una ventana modal" }
        ShareStateData.nombre = nombre.text
        ShareStateData.edad = edad.text
        ShareStateData.email = email.text
        RoutesManager.initModalStage()
    }

    private fun onGetAlumnosClick() {
        logger.debug { "Conseguimos todos los alumnos" }
        val alumnos = repository.getAll()
        if(alumnos.size == 0){
            Alert(Alert.AlertType.INFORMATION).apply {
                title = "Listado de alumnos"
                headerText = null
                contentText = "Aun no hay alumnos introducidos."
            }.run{
                showAndWait()
            }
        }else{
            Alert(Alert.AlertType.INFORMATION).apply {
                title = "Listado de alumnos"
                headerText = null
                contentText = alumnos.joinToString(separator = ";", prefix = "[", postfix = "]")
            }.run{
                showAndWait()
            }
        }
    }

    private fun onSaveClick() {
        logger.debug { "Guardamos y presentamos a un alumno" }
        validateAlumno(
            nombre.text,
            edad.text,
            email.text
        )
            .onSuccess {
                repository.save(it)
                Alert(Alert.AlertType.CONFIRMATION).apply {
                    headerText = null
                    title = "Alumno creado correctamente"
                    contentText = "El alumno: "+it.nombre+", ha sido creado correctamente. \n Por cierto, hola "+it.nombre
                }.run{
                    onCleanTextClick()
                    showAndWait()
                }
            }
            .onFailure {
                marcarCamposErroneos(it.message)
                Alert(Alert.AlertType.ERROR).apply {
                    headerText = null
                    title = "Error al crear un alumno"
                    contentText = it.message
                }.run{
                    showAndWait()
                }
            }
    }

    private fun marcarCamposErroneos(message: String) {
        if(message.contains("nombre")){
            nombre.setStyle("-fx-background-color: red;");
            edad.setStyle("-fx-background-color: white;");
            email.setStyle("-fx-background-color: white;");
        }else{
            if(message.contains("edad")){
                nombre.setStyle("-fx-background-color: white;");
                edad.setStyle("-fx-background-color: red;");
                email.setStyle("-fx-background-color: white;");
            }else{
                nombre.setStyle("-fx-background-color: white;");
                edad.setStyle("-fx-background-color: white;");
                email.setStyle("-fx-background-color: red;");
            }
        }
    }

    private fun onCleanTextClick() {
        logger.debug { "Se limpia todos los cuadros de texto existentes" }
        nombre.text = ""
        edad.text = ""
        email.text = ""
        nombre.setStyle("-fx-background-color: white;");
        edad.setStyle("-fx-background-color: white;");
        email.setStyle("-fx-background-color: white;");
    }
}