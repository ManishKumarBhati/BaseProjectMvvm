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
    const val Timber = "4.7.1"
    const val Hilt = "2.35"
    const val Retrofit = "2.6.0"
    const val Room = "2.1.0"
    const val OkHttp = "3.12.3"
    const val OkIo = "1.17.4"
}

object Dependencies {
    val KotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Version.KotlinVersion}"
    val KtxCore = "androidx.core:core-ktx:1.3.1"
    val AppCompat = "androidx.appcompat:appcompat:1.2.0"
    val Material = "com.google.android.material:material:1.2.1"
    val ConstraintLayout = "androidx.constraintlayout:constraintlayout:2.0.1"
    val Junit = "junit:junit:4.13.2"
    val JunitTest = "androidx.test.ext:junit:1.1.2"
    val EspressoCore = "androidx.test.espresso:espresso-core:3.3.0"
    val Gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KotlinVersion}"
    val Timber = "com.jakewharton.timber:timber:${Version.Timber}"
}

object Gradle {
    val Build = "com.android.tools.build:gradle:${Version.BuildGradle}"
    val Kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KotlinVersion}"
    val Hilt = "com.google.dagger:hilt-android-gradle-plugin:${Version.Hilt}"
}

object Hilt {
    val Compiler = "com.google.dagger:hilt-android-compiler:${Version.Hilt}"
    val Core = "com.google.dagger:hilt-android:${Version.Hilt}"
}

object Retrofit {
    const val Core = "com.squareup.retrofit2:retrofit:${Version.Retrofit}"
    const val Adapter = "com.squareup.retrofit2:adapter-rxjava2:${Version.Retrofit}"
    const val Moshi = "com.squareup.retrofit2:converter-moshi:2.4.0"
}

object OkHttp {
    const val Okio = "com.squareup.okio:okio:${Version.OkIo}"
    const val Core = "com.squareup.okhttp3:okhttp:${Version.OkHttp}"
    const val Logger = "com.squareup.okhttp3:logging-interceptor:${Version.OkHttp}"
}

object Room {
    const val Core = "androidx.room:room-runtime:${Version.Room}"
    const val Compiler = "androidx.room:room-compiler:${Version.Room}"
}
object RxJava{
    const val Java="io.reactivex.rxjava2:rxjava:2.2.10"
    const val Kotlin="io.reactivex.rxjava2:rxkotlin:2.3.0"
    const val Android="io.reactivex.rxjava2:rxandroid:2.1.1"
    const val Relay= "com.jakewharton.rxrelay2:rxrelay:2.1.0"
    const val Prefrence= "com.f2prateek.rx.preferences2:rx-preferences:2.0.0"

}