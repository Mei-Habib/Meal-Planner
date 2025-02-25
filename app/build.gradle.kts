plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.mealplanner"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mealplanner"
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

    //RX
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.6")
    //Glide
    implementation(libs.glide)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    //BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:33.9.0"))
    //Firebase Authentication library
    implementation("com.google.firebase:firebase-auth:23.0.0")
    //Google Play services library
    implementation("com.google.android.gms:play-services-auth:21.1.1")
    //Credential Manager
//    implementation("androidx.credentials:credentials:1.5.0-rc01")
//    implementation("androidx.credentials:credentials-play-services-auth:1.5.0-rc01")
//    implementation("androidx.credentials:credentials:1.3.0")
//    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
//    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
    implementation(libs.firebase.firestore)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.google.code.gson:gson:2.12.1")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation(libs.googleid)
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.6")
    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")
    implementation("androidx.room:room-rxjava3:2.6.1")
    //lottie
    implementation("com.airbnb.android:lottie:6.3.0")
    //Splash Screen
    implementation("androidx.core:core-splashscreen:1.1.0-alpha02")
    //Navigation
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")
    // video player
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}