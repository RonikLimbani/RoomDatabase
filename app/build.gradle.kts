plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
//    id("com.google.devtools.ksp")

}

android {
    namespace = "com.ronik.roomdatabasewithmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ronik.roomdatabasewithmvvm"
        minSdk = 24
        targetSdk = 33
        multiDexEnabled = true
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
    }

    buildFeatures {
        dataBinding= true
    }
    kapt {
        correctErrorTypes = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val nav_version = "2.7.2"
    val room_version = "2.5.2"
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Room dependencies
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
//    implementation("androidx.room:room-ktx:2.5.2")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // To use Kotlin Symbol Processing (KSP)
//    ksp("androidx.room:room-compiler:$room_version")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")


//    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation ("com.google.dagger:hilt-android:2.48")
    annotationProcessor ("com.google.dagger:hilt-compiler:2.48")

//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    val lifecycle_version = "2.6.2"


    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")



//    kapt ("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")

    implementation("androidx.activity:activity-ktx:1.7.2")

    val multidex_version = "2.0.1"
    implementation("androidx.multidex:multidex:$multidex_version")
}