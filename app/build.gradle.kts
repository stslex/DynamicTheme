plugins {
    id("dynamictheme.android.application")
    id("dynamictheme.android.application.compose")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.ksp)
}

@Suppress("UnstableApiUsage")
android {
    namespace = "com.slex.dynamictheme"

    defaultConfig {
        applicationId = "com.stslex.dynamictheme"
        versionCode = 1
        versionName = "1.0"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    kotlin {
        sourceSets.main {
            kotlin.srcDir("build/generated/ksp/main/kotlin")
        }
        sourceSets.test {
            kotlin.srcDir("build/generated/ksp/test/kotlin")
        }
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
    implementation(project(":feature-home"))

    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")

    libs.apply {
        implementation(ksp.api)
        implementation(androidx.room.ktx)
        implementation(androidx.room.runtime)
        annotationProcessor(androidx.room.compiler)
        ksp(androidx.room.compiler)
    }
}