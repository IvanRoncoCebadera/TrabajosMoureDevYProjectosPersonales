package com.example.saludaryguardar_java.controllers;

import com.example.saludaryguardar_java.models.Alumno;
import com.example.saludaryguardar_java.repository.AlumnoRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.saludaryguardar_java.validator.AlumnoValidator.validateAlumno;

public class HelloController implements Initializable {

    private AlumnoRepository repository = new AlumnoRepository();

    @FXML
    private TextField nombre;
    @FXML
    private TextField edad;
    @FXML
    private TextField email;
    @FXML
    private Button botonSaludar;
    @FXML
    private Button botonLimpiar;
    @FXML
    private Button botonListarAlumnos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void onCleanTextClick(ActionEvent event) {
        nombre.setText("");
        edad.setText("");
        email.setText("");
        nombre.setStyle("-fx-background-color: white;");
        edad.setStyle("-fx-background-color: white;");
        email.setStyle("-fx-background-color: white;");
    }

    public void onSaludarYGuardarClick(ActionEvent event) {
        try{
            Alumno alumno = validateAlumno(nombre.getText(), edad.getText(), email.getText());
            repository.save(alumno);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alumno guardado");
            alert.setHeaderText(null);
            alert.setContentText("Hola "+alumno.getNombre()+", tus datos han sido registrados correctamente.");
            alert.showAndWait();
            onCleanTextClick(null);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al saludar y guardar");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            ponerCamposEnRojo(e.getMessage());
        }
    }

    private void ponerCamposEnRojo(String message) {
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

    public void onGetAllAumnosClick(ActionEvent event) {
        if(repository.getAll().size() > 0) {
            String mensaje = "";
            List<Alumno> alumnos = repository.getAll();
            for (int i = 0; i < alumnos.size(); i++) {
                if (i != alumnos.size() - 1) {
                    mensaje += alumnos.get(i).toString() + "||";
                } else {
                    mensaje += alumnos.get(i).toString();
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Listado de alumnos");
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Listado de alumnos");
            alert.setHeaderText(null);
            alert.setContentText("Aun no hay alumnos para listar.");
            alert.showAndWait();
        }
    }
}