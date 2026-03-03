rootProject.name = "Canimation"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")

// canimation library modules
include(":canimation-core")
include(":canimation-presets")
include(":canimation-a11y")
include(":canimation-diagnostics")
include(":canimation-test")
include(":canimation-platform-android")
include(":canimation-platform-desktop")
include(":canimation-platform-ios")
include(":canimation-platform-web")

project(":canimation-core").projectDir = file("modules/canimation-core")
project(":canimation-presets").projectDir = file("modules/canimation-presets")
project(":canimation-a11y").projectDir = file("modules/canimation-a11y")
project(":canimation-diagnostics").projectDir = file("modules/canimation-diagnostics")
project(":canimation-test").projectDir = file("modules/canimation-test")
project(":canimation-platform-android").projectDir = file("modules/canimation-platform-android")
project(":canimation-platform-desktop").projectDir = file("modules/canimation-platform-desktop")
project(":canimation-platform-ios").projectDir = file("modules/canimation-platform-ios")
project(":canimation-platform-web").projectDir = file("modules/canimation-platform-web")