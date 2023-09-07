package com.example.saludaryguardar_kotlin

import com.example.saludaryguardar_kotlin.routes.RoutesManager
import javafx.application.Application
import javafx.stage.Stage
import java.time.LocalTime

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        RoutesManager.apply {
            app = this@HelloApplication
        }
        RoutesManager.initMainStage(stage)
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}