plugins {
    `kotlin-dsl`
}

group = "com.stslex.dynamictheme.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin{
    plugins{
        register("androidApplicationCompose") {
            id = "dynamictheme.android.application.compose"
            implementationClass = "AndroidApplicationComposePlugin"
        }
        register("androidApplication") {
            id = "dynamictheme.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibraryCompose") {
            id = "dynamictheme.android.library.compose"
            implementationClass = "AndroidLibraryComposePlugin"
        }
        register("androidLibrary") {
            id = "dynamictheme.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
    }
}