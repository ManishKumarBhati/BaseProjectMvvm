plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
//    id("com.google.gms.google-services") todo(uncomment this once after adding **google-service.json**)
//    id("com.google.firebase.crashlytics")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("..\\manishbhati.jks")
            storePassword = "manish"
            keyAlias = "manish"
            keyPassword = "manish"
        }
        create("release") {
            storeFile = file(".\\manishbhati.jks")
            storePassword = "manish"
            keyAlias = "manish"
            keyPassword = "manish"
        }
    }
    compileSdkVersion(Version.CompileSdk)
    buildToolsVersion = Version.BuildToolsVersion

    defaultConfig {
        applicationId = "com.bmk.baseproject"
        minSdkVersion(Version.MinSdk)
        targetSdkVersion(Version.TargetSdk)
        versionCode = Version.VersionCode
        versionName = Version.VersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            multiDexEnabled = true
        }
        getByName("debug") {
            applicationIdSuffix = "debug"
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    lintOptions {
        // Turns off checks for the issue IDs you specify.
        disable("TypographyFractions")
        disable("TypographyQuotes")
        // Turns on checks for the issue IDs you specify. These checks are in
        // addition to the default lint checks.
        enable("RtlHardcoded")
        enable("RtlCompat")
        enable("RtlEnabled")
        lintConfig = (file("lint.xml"))
        // To enable checks for only a subset of issue IDs and ignore all others,
        // list the issue IDs with the 'check' property instead. This property overrides
        // any issue IDs you enable or disable using the properties above.
        checkOnly("NewApi", "InlinedApi", "HandlerLeak")
        baseline(file("lint-baseline.xml"))
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
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
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
    implementation(Androidx.Preferences)
    implementation(Dependencies.Stetho)

    //Hilt
    implementation(Hilt.Core)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(Hilt.Compiler)

    kapt(Hilt.ViewModelCompiler)

    //LifeCycle
    implementation(LifeCycle.Core)
    implementation(LifeCycle.ViewModel)
    implementation(LifeCycle.Runtime)
    implementation(LifeCycle.LiveData)

    //Room
    implementation(Room.Core)
    implementation(Room.CoreKtx)
    kapt(Room.Compiler)

    //Retrofit
    implementation(Retrofit.Core)
    implementation(Retrofit.Moshi)
    implementation(Retrofit.Adapter)

    //okHttp
    implementation(OkHttp.Core)
    implementation(OkHttp.Logger)
    implementation(OkHttp.Okio)


    //Navigation
    implementation(Navigation.Fragment)
    implementation(Navigation.Ui)

    implementation(Authenticate.Biometric)

    //UnitTest
    testImplementation(UnitTest.Junit)
    androidTestImplementation(UnitTest.JunitTest)
//    androidTestImplementation(UnitTest.EspressoCore)
    androidTestImplementation(UnitTest.Robolectric)

    //Firebase todo(uncomment this once after adding **google-service.json**)
//    implementation(platform(Firebase.BOM))
//    implementation(Firebase.Mssaging)
//    implementation(Firebase.Analytics)
//    implementation(Firebase.Crashlytics)

    implementation(project(":domain"))
    implementation(project(":data"))
}