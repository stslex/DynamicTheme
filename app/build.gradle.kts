plugins {
    id("dynamictheme.android.application")
    id("dynamictheme.android.application.compose")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.21"
}

@Suppress("UnstableApiUsage")
android {
    namespace = "com.slex.dynamictheme"

    defaultConfig {
        applicationId = "com.stslex.dynamictheme"
        versionCode = 1
        versionName = "1.0"
        targetSdk = 33

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
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-navigation"))
    implementation(project(":core-data-source"))
    implementation(project(":feature-home"))
    implementation(libs.androidx.work.runtime)
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("androidx.core:core-google-shortcuts:1.1.0")
    implementation("androidx.glance:glance-appwidget:1.0.0-alpha05")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}
