plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Version.CompileSdk)
    buildToolsVersion = Version.BuildToolsVersion

    defaultConfig {
        minSdkVersion(Version.MinSdk)
        targetSdkVersion(Version.TargetSdk)
        versionCode = Version.VersionCode
        versionName = Version.VersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles ="consumer-rules.pro"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Dependencies.KotlinStd)
    implementation(Androidx.KtxCore)
    implementation(Dependencies.Timber)

    //UnitTest
//    testImplementation(Dependencies.Junit)
//    androidTestImplementation(Dependencies.JunitTest)
//    androidTestImplementation(Dependencies.EspressoCore)

    //Hilt
    implementation(Hilt.Core)
    kapt(Hilt.Compiler)

    //Room
    implementation(Room.Core)
    implementation(Room.RxJava)
    kapt(Room.Compiler)

    //Retrofit
    implementation(Retrofit.Core)
    implementation(Retrofit.Moshi)
    implementation(Retrofit.Adapter)

    //okHttp
    implementation(OkHttp.Core)
    implementation(OkHttp.Logger)
    implementation(OkHttp.Okio)

    //RxJava
    implementation(RxJava.Java)
    implementation(RxJava.Kotlin)
    implementation(RxJava.Android)

    implementation(project(":domain"))
}