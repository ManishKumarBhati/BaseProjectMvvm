import javax.swing.SpringLayout

object Version {
    const val CompileSdk = 30
    const val TargetSdk = 30
    const val MinSdk = 16
    const val VersionCode = 1
    const val VersionName = "1.0.0"
    const val BuildToolsVersion = "30.0.3"
    const val KotlinVersion = "1.5.0"
    const val BuildGradle = "4.2.1"
}

object Dependencies {
val KotlinStd="org.jetbrains.kotlin:kotlin-stdlib:${Version.KotlinVersion}"
val KtxCore="androidx.core:core-ktx:1.3.1"
val AppCompat="androidx.appcompat:appcompat:1.2.0"
val Material="com.google.android.material:material:1.2.1"
val ConstraintLayout="androidx.constraintlayout:constraintlayout:2.0.1"
val Junit="junit:junit:4.13.2"
val JunitTest="androidx.test.ext:junit:1.1.2"
val EspressoCore="androidx.test.espresso:espresso-core:3.3.0"
val Gradle ="org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KotlinVersion}"
}
object Gradle {
    val Build = "com.android.tools.build:gradle:${Version.BuildGradle}"
val Kotlin="org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KotlinVersion}"
}