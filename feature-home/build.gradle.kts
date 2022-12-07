plugins {
    id("dynamictheme.android.library")
    id("dynamictheme.android.library.compose")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-test"))
    implementation(project(":core-navigation"))
    implementation(project(":core-data-source"))
}

android.namespace = "com.stslex.feature_home"
