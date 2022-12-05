plugins {
    id("dynamictheme.android.library")
}

dependencies {
    api(libs.androidx.core.ktx)
    api(libs.bundles.koin)
}

android.namespace = "com.stslex.core"
