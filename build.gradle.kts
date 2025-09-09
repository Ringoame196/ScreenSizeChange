plugins {
    kotlin("jvm") version "2.1.10"
}

group = "com.github.ringoame196"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("net.java.dev.jna:jna:5.14.0")
    implementation("net.java.dev.jna:jna-platform:5.14.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(22)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.github.ringoame196.MainKt"
    }
    configurations["compileClasspath"].forEach {
            file: File -> from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}