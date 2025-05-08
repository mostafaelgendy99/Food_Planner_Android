plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.aklaholic"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.aklaholic"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Retrofit core
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

// Gson converter for JSON parsing
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// OkHttp (optional, but recommended)
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0" ) // Add Glide dependency here
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0") // for annotation processing
    // Room Database
    implementation ("androidx.room:room-runtime:2.6.1" ) // Room runtime
    annotationProcessor ("androidx.room:room-compiler:2.6.1" ) // Annotation processor (for Room entities)

    implementation("com.airbnb.android:lottie:6.6.6")

    // For Kotlin users, use kapt instead of annotationProcessor
    // kapt "androidx.room:room-compiler:2.6.1"

    // Room Paging (optional, if using paging with Room)
    implementation ("androidx.room:room-paging:2.6.1")

}