plugins {
    id("dynamictheme.android.library")
}

dependencies {
    implementation(project(":core"))
    api(libs.androidx.navigation.compose)
}

android.namespace = "com.stslex.core_navigation"
