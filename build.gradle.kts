plugins {
    kotlin("jvm") version "1.9.0"
    id("io.qameta.allure") version "2.11.2"
    application
}

group = "ru.egor"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val junitVersion = "5.10.0"
val selenideVersion = "6.19.0"
val allureVersion = "2.24.0"

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("com.codeborne:selenide:$selenideVersion")
    testImplementation("io.qameta.allure:allure-selenide:${allureVersion}")
    testImplementation("io.qameta.allure:allure-junit5:${allureVersion}")
    testImplementation("io.qameta.allure:allure-assertj:${allureVersion}")
    testImplementation("org.slf4j:slf4j-simple:2.0.9")
    testImplementation("org.aeonbits.owner:owner:1.0.12")
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