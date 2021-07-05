plugins {
    id("com.android.application")
    id("kotlin-android")
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
    implementation(Dependencies.KtxCore)
    implementation(Dependencies.AppCompat)
    implementation(Dependencies.Timber)
    implementation(Dependencies.Material)
    implementation(Dependencies.ConstraintLayout)
    testImplementation(Dependencies.Junit)
    androidTestImplementation(Dependencies.JunitTest)
    androidTestImplementation(Dependencies.EspressoCore)
}