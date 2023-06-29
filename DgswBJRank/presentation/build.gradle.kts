plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("androidx.navigation.safeargs.kotlin")
    id ("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
}

android {
    namespace="com.bestswlkh0310.presentation"

    defaultConfig {
        compileSdk = 34
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    val navVersion = "2.5.3"

    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    kapt ("com.android.databinding:compiler:3.1.4")

    val hiltVersion = "2.44.2"
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("com.google.dagger:hilt-android:2.44.2")

    val okttpVersion = "4.10.0"
    implementation ("com.squareup.okhttp3:okhttp:$okttpVersion")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okttpVersion")

    val retrofitVersion = "2.9.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    implementation ("androidx.core:core-ktx:1.10.1")
    implementation ("androidx.activity:activity-ktx:1.7.2")
    implementation ("androidx.fragment:fragment-ktx:1.5.7")
    implementation ("com.google.android.material:material:1.1.0")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    implementation (project(":data"))
    implementation (project(":domain"))
}