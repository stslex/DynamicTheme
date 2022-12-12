plugins {
    id("dynamictheme.android.application")
    id("dynamictheme.android.application.compose")
    id("org.jetbrains.kotlin.android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "com.slex.dynamictheme"

    defaultConfig {
        applicationId = "com.stslex.dynamictheme"
        versionCode = 1
        versionName = "1.0"

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
    implementation(project(":core-test"))
    implementation(project(":core-ui"))
    implementation(project(":core-navigation"))
    implementation(project(":core-data-source"))
    implementation(project(":feature-home"))
    implementation(libs.androidx.work.runtime)
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("androidx.core:core-google-shortcuts:1.1.0")
}