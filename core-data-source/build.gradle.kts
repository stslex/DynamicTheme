plugins {
    id("dynamictheme.android.library")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":core"))

    libs.apply {
        api(ksp.api)
        api(androidx.room.ktx)
        api(androidx.room.runtime)
        annotationProcessor(androidx.room.compiler)
        ksp(androidx.room.compiler)
    }
}

android {
    namespace = "com.stslex.core_data_source"

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
}