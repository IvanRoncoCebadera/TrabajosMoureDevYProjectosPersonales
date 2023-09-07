plugins {
    kotlin("jvm") version "1.8.0"
    application
    // Para Koin Annotation
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Koin
    implementation("io.insert-koin:koin-core:3.4.0")
    implementation("io.insert-koin:koin-annotations:1.2.0") // Si usamos Koin con KSP Anotaciones
    ksp("io.insert-koin:koin-ksp-compiler:1.2.0") // Si usamos Koin con KSP Anotaciones
    implementation("io.insert-koin:koin-logger-slf4j:3.4.0") // Para el logger de Koin

    // Test con JUnit 5 básico
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

// Para Koin Annotations, directorio donde se encuentran las clases compiladas
// KSP - To use generated sources
sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}