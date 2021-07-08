plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Version.CompileSdk)
    buildToolsVersion = Version.BuildToolsVersion

    defaultConfig {
        applicationId = "com.citiustech.baseproject"
        minSdkVersion(Version.MinSdk)
        targetSdkVersion(Version.TargetSdk)
        versionCode = Version.VersionCode
        versionName = Version.VersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.KotlinStd)
    implementation(Androidx.KtxCore)
    implementation(Androidx.AppCompat)
    implementation(Dependencies.Timber)
    implementation(Dependencies.Material)
    implementation(Androidx.ConstraintLayout)
    implementation(Androidx.Fragment)

    //Hilt
    implementation(Hilt.Core)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(Hilt.Compiler)
    implementation(Hilt.ViewModel)
    kapt(Hilt.ViewModelCompiler)

    //LifeCycle
    implementation(LifeCycle.Core)
    implementation(LifeCycle.ViewModel)
    implementation(LifeCycle.Runtime)
    implementation(LifeCycle.LiveData)

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

    //Navigation
    implementation(Navigation.Fragment)
    implementation(Navigation.Ui)

    //UnitTest
    testImplementation(UnitTest.Junit)
    androidTestImplementation(UnitTest.JunitTest)
    androidTestImplementation(UnitTest.EspressoCore)

    implementation(project(":domain"))
    implementation(project(":data"))
}