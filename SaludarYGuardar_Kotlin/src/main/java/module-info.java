module com.example.saludaryguardar_kotlin {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires io.github.microutils.kotlinlogging;
    requires org.slf4j;
    requires kotlin.result.jvm;

    opens com.example.saludaryguardar_kotlin.controllers to javafx.fxml;
    exports com.example.saludaryguardar_kotlin;
}