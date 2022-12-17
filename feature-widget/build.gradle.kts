plugins {
    id("dynamictheme.android.library")
    id("dynamictheme.android.library.compose")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.21"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))

    implementation(libs.androidx.work.runtime)
    implementation("androidx.glance:glance-appwidget:1.0.0-alpha05")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

android.namespace = "com.stslex.feature_widget"