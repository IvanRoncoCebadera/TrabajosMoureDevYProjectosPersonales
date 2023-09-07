module com.example.saludaryguardar_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.saludaryguardar_java to javafx.fxml;
    exports com.example.saludaryguardar_java;
    exports com.example.saludaryguardar_java.controllers;
    opens com.example.saludaryguardar_java.controllers to javafx.fxml;
}