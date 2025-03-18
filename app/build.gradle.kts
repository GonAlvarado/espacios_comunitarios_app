import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.gonnadev.espacioscomunitariosapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gonnadev.espacioscomunitariosapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val localProperties = Properties()
    val localPropertiesFile = File(rootDir, "secret.properties")
    if (localPropertiesFile.exists() && localPropertiesFile.isFile) {
        localPropertiesFile.inputStream().use {
            localProperties.load(it)
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "MAPS_API_KEY", localProperties.getProperty("MAPS_API_KEY"))
            resValue("string", "MAPS_API_KEY", localProperties.getProperty("MAPS_API_KEY"))
        }
        debug {
            buildConfigField("String", "MAPS_API_KEY", localProperties.getProperty("MAPS_API_KEY"))
            resValue("string", "MAPS_API_KEY", localProperties.getProperty("MAPS_API_KEY"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        resValues = true
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    val navigationVersion = "2.8.6"
    val retrofitVersion = "2.11.0"

    //Maps
    implementation("com.google.android.gms:play-services-maps:19.1.0")

    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.3")

    //DaggerHilt
    implementation("com.google.dagger:hilt-android:2.55")
    kapt("com.google.dagger:hilt-android-compiler:2.55")

    //NavComponent
    implementation("androidx.navigation:navigation-fragment-ktx:${navigationVersion}")
    implementation("androidx.navigation:navigation-ui:${navigationVersion}")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:${retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${retrofitVersion}")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //SplashScreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}