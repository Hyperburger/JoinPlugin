plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
    id ("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.hyperburger"
version = "2.4"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    // PlaceholderAPI
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.minebench.de/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.10.10")
    compileOnly("net.kyori:adventure-api:4.8.1")
    compileOnly("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.github.cryptomorin:XSeries:8.3.0")
    compileOnly(fileTree("libs/compile"))
    implementation(fileTree("libs/implement"))
}