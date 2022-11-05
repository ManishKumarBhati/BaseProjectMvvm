// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    val kotlin_version by extra("1.7.20")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Gradle.Build)
        classpath(Gradle.Kotlin)
//        classpath(Gradle.FireBase)todo(uncomment this once after adding **google-service.json**)
        classpath(Gradle.Hilt)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}