plugins {
    id("dynamictheme.android.library")
}


dependencies {
    implementation(project(":core"))
    api(libs.bundles.test)
}

android.namespace = "com.stslex.core_test"
