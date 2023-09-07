import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

// Para hacer un JAR ejecutable
tasks.jar {
    manifest {
        // Aquí se pone el nombre del fichero que tiene el método main
        // que queremos que se lance
        // si se llama Main.kt, se pone MainKt, si se llama Otro.kt, se pone OtroKt
        attributes["Main-Class"] = "ModelsSalaDeCine.SimulacionSalaDeCineKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}