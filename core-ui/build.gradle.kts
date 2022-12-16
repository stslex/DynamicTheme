plugins {
    id("dynamictheme.android.library")
    id("dynamictheme.android.library.compose")
}

dependencies {
    implementation(project(":core"))

    val composeBom = platform(libs.androidx.compose.bom)
    api(composeBom)
    testApi(composeBom)
    api(libs.bundles.compose)
    api(libs.bundles.accompanist)
    api(libs.androidx.constraintlayout.compose)
    testApi(libs.bundles.test.ui)
}

android.namespace = "com.stslex.core_ui"