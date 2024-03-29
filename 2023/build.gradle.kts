import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.21"
    idea
}

group = "sh.weller.aoc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

dependencies {

//    implementation("org.jgrapht:jgrapht-core:1.5.1")
//    implementation("org.magicwerk:brownies-collections:0.9.15")
//    implementation("org.jetbrains.kotlinx:multik-api:0.1.1")
//    implementation("org.jetbrains.kotlinx:multik-default:0.1.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "21"
}
