object Version {
    const val CompileSdk = 30
    const val TargetSdk = 30
    const val MinSdk = 23
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
    const val RxBinding = "4.0.0"
    const val Groupie = "2.9.0"
    const val LifeCycle = "2.4.0-alpha02"
    const val Navigation = "2.3.5"
    const val Glide = "4.12.0"
}

object Dependencies {
    val KotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Version.KotlinVersion}"
    val Material = "com.google.android.material:material:1.2.1"
    val Gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KotlinVersion}"
    val Timber = "com.jakewharton.timber:timber:${Version.Timber}"
    val Stetho = "com.facebook.stetho:stetho:1.5.1"
    val Gson = "com.google.code.gson:gson:2.8.5"
}

object LifeCycle {
    val Core = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LifeCycle}"
    val Runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LifeCycle}"
    val LiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LifeCycle}"
}

object Androidx {
    val KtxCore = "androidx.core:core-ktx:1.3.1"
    val AppCompat = "androidx.appcompat:appcompat:1.2.0"
    val RecyclerView = "androidx.recyclerview:recyclerview:1.2.1"
    val ConstraintLayout = "androidx.constraintlayout:constraintlayout:2.0.1"
    val Fragment = "androidx.fragment:fragment-ktx:1.2.5"
    val Preferences = "androidx.security:security-crypto:1.1.0-alpha02"
}

object UnitTest {
    val Junit = "junit:junit:4.13.2"
    val JunitTest = "androidx.test.ext:junit:1.1.2"
    val EspressoCore = "androidx.test.espresso:espresso-core:3.3.0"
    val Robolectric = "org.robolectric:robolectric:4.4"
}

object Gradle {
    val Build = "com.android.tools.build:gradle:${Version.BuildGradle}"
    val Kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KotlinVersion}"
    val Hilt = "com.google.dagger:hilt-android-gradle-plugin:${Version.Hilt}"
}

object Hilt {
    val Compiler = "com.google.dagger:hilt-android-compiler:${Version.Hilt}"
    val Core = "com.google.dagger:hilt-android:${Version.Hilt}"
    val ViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
    val ViewModelCompiler = "androidx.hilt:hilt-compiler:1.0.0-alpha01"
}

object Retrofit {
    const val Core = "com.squareup.retrofit2:retrofit:${Version.Retrofit}"
    const val Adapter = "com.squareup.retrofit2:adapter-rxjava2:${Version.Retrofit}"
    const val Moshi = "com.squareup.retrofit2:converter-moshi:2.4.0"
    const val Gson = "com.squareup.retrofit2:converter-gson:${Version.Retrofit}"
}

object OkHttp {
    const val Okio = "com.squareup.okio:okio:${Version.OkIo}"
    const val Core = "com.squareup.okhttp3:okhttp:${Version.OkHttp}"
    const val Logger = "com.squareup.okhttp3:logging-interceptor:${Version.OkHttp}"
}

object Room {
    const val Core = "androidx.room:room-runtime:${Version.Room}"
    const val Compiler = "androidx.room:room-compiler:${Version.Room}"
    const val RxJava = "androidx.room:room-rxjava2:${Version.Room}"
    const val SqlCipher = "net.zetetic:android-database-sqlcipher:4.4.2"
    const val Sqlite = "androidx.sqlite:sqlite:2.0.1"
}

object RxJava {
    const val Java = "io.reactivex.rxjava2:rxjava:2.2.10"
    const val Kotlin = "io.reactivex.rxjava2:rxkotlin:2.3.0"
    const val Android = "io.reactivex.rxjava2:rxandroid:2.1.1"
    const val Prefrence = "com.f2prateek.rx.preferences2:rx-preferences:2.0.0"
}

object Binding {
    const val AppCompat = "com.jakewharton.rxbinding4:rxbinding-appcompat:${Version.RxBinding}"
    const val Material = "com.jakewharton.rxbinding4:rxbinding-material:${Version.RxBinding}"
    const val Core = "com.jakewharton.rxbinding4:rxbinding-core:${Version.RxBinding}"
}

object Groupie {
    const val Core = "com.xwray:groupie:${Version.Groupie}"
    const val ViewBinding = "com.xwray:groupie-viewbinding:${Version.Groupie}"
    const val Extension = "com.xwray:groupie-kotlin-android-extensions:${Version.Groupie}"
}

object Glide {
    val Core = "com.github.bumptech.glide:glide:${Version.Glide}"
    val Compiler = "com.github.bumptech.glide:compiler:${Version.Glide}"
}

object Navigation {
    const val Fragment = "androidx.navigation:navigation-fragment-ktx:${Version.Navigation}"
    const val Ui = "androidx.navigation:navigation-ui-ktx:${Version.Navigation}"
}
