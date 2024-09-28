plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
  //  id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.androidutilitiesnew"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidutilitiesnew"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // Defining dimensions for flavors using the flavorDimensions property
    flavorDimensions.addAll(listOf("version"))
  //  flavorDimensions.addAll(listOf("version","environment"))
    productFlavors{
        create("free"){
            dimension = "version"
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"
            minSdk = 30
        }
        create("paid"){
            dimension = "version"
            applicationIdSuffix = ".paid"
            versionNameSuffix = "-paid"
            minSdk = 21
        }
        /*create("minSdk30"){
            dimension = "environment"
            applicationIdSuffix = ".minSdk30"
            versionNameSuffix = "-minSdk30"
            minSdk  = 30
        }
        create("minSdk21"){
            dimension = "environment"
            applicationIdSuffix = ".minSdk21"
            versionNameSuffix = "-minSdk21"
            minSdk  = 21
        }*/
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        //build types
        create("alpha"){
            isMinifyEnabled =  false
            applicationIdSuffix = ".alpha"
            versionNameSuffix = "-alpha"
        }

        /*create("beta"){
            isMinifyEnabled =  false
            applicationIdSuffix = ".beta"
            versionNameSuffix = "-beta"
        }*/
      /*  getByName("debug") {
            isMinifyEnabled = false
        }*/
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)

    // Exclude from all release variants (any flavor + release build type)
    configurations.all {
        if (name.contains("release", ignoreCase = true)) {
            exclude(group = "androidx.ui", module = "ui-test-manifest")
        }
    }

    // Room
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
  //  ksp (libs.androidx.room.compiler)

    // Kotlin Extensions and Coroutines support for Room
    implementation (libs.androidx.room.ktx)
    // Retrofit
    implementation (libs.retrofit)
 //   implementation (libs.converter.gson)
    //implementation (libs.logging.interceptor)
}