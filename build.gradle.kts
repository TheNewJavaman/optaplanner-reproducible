import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "net.javaman"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.optaplanner:optaplanner-core:8.12.0.Final")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

application {
    mainClass.set("net.javaman.optaplanner_reproducible.Main")
}