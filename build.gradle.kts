import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"

    java
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.Minestom:Minestom:b3d81f274c")
    compileOnly("com.github.EmortalMC:Immortal:ee19aec6bd")
    compileOnly("com.github.EmortalMC:NBStom:fd3da7bf91")
//    compileOnly("com.github.EmortalMC:TNT:f0680e2013")


    compileOnly("org.litote.kmongo:kmongo-coroutine-serialization:4.7.0")

    //implementation("com.github.KrystilizeNevaDies:Scaffolding:2303491258")
    compileOnly("com.github.EmortalMC:Acquaintance:b07cb2e120")

    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
}

tasks {
    processResources {
        filesMatching("extension.json") {
            expand(project.properties)
        }
    }

    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveBaseName.set(project.name)
        mergeServiceFiles()
        minimize {
            //exclude(dependency("mysql:mysql-connector-java:8.0.29"))
        }
    }

    build { dependsOn(shadowJar) }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()

compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}
