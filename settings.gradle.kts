enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    @Suppress("UnstableApiUsage")
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DynamicTheme"

include(":app")
include(":core")
include(":core-ui")
include(":core-navigation")
include(":feature-home")
include(":core-data-source")
include(":feature-widget")
