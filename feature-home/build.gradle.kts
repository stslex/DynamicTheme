plugins {
    id("dynamictheme.android.library")
    id("dynamictheme.android.library.compose")
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-test"))
    implementation(project(":core-navigation"))
    implementation ("com.github.skydoves:landscapist-glide:2.1.0")

    libs.apply {
        implementation(ksp.api)
        implementation(androidx.room.ktx)
        implementation(androidx.room.runtime)
        annotationProcessor(androidx.room.compiler)
        ksp(androidx.room.compiler)
    }
}

android {
    kotlin {
        sourceSets.main {
            kotlin.srcDir("build/generated/ksp/main/kotlin")
        }
        sourceSets.test {
            kotlin.srcDir("build/generated/ksp/test/kotlin")
        }
    }
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }
    namespace = "com.stslex.feature_home"
}
