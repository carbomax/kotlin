val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val dynamodbEnhancedVersion: String by project.extra { "2.12.0" }
val koin_version: String by project.extra {  "3.1.2" }

plugins {
    application
    kotlin("jvm") version "1.5.30"
}

group = "com.devhighlevel"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-gson:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("software.amazon.awssdk:dynamodb-enhanced:$dynamodbEnhancedVersion")
    // https://mvnrepository.com/artifact/software.amazon.awssdk/netty-nio-client
    implementation("software.amazon.awssdk:netty-nio-client:2.17.27")

    // Koin for Ktor
    implementation ("io.insert-koin:koin-ktor:$koin_version")
    // SLF4J Logger
    implementation ("io.insert-koin:koin-logger-slf4j:$koin_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}