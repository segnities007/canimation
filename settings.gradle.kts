rootProject.name = "Canimation"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
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
        exclusiveContent {
            forRepository {
                ivy("https://nodejs.org/dist") {
                    name = "Node.js distributions"
                    patternLayout {
                        artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]")
                    }
                    metadataSources {
                        artifact()
                    }
                }
            }
            filter {
                includeModule("org.nodejs", "node")
            }
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")
include(":androidApp")

// canimation library modules
include(":canimation-core")
include(":canimation-tokens")
include(":canimation-primitives")
include(":canimation-semantics")
include(":canimation-recipes")
include(":canimation-runtime")
include(":canimation-presets")
include(":canimation-a11y")
include(":canimation-diagnostics")
include(":canimation-test")
include(":canimation-test-kit")
include(":canimation-compat")
include(":canimation-experimental")
include(":canimation-platform-android")
include(":canimation-platform-desktop")
include(":canimation-platform-ios")
include(":canimation-platform-web")

project(":canimation-core").projectDir = file("modules/canimation-core")
project(":canimation-tokens").projectDir = file("modules/canimation-tokens")
project(":canimation-primitives").projectDir = file("modules/canimation-primitives")
project(":canimation-semantics").projectDir = file("modules/canimation-semantics")
project(":canimation-recipes").projectDir = file("modules/canimation-recipes")
project(":canimation-runtime").projectDir = file("modules/canimation-runtime")
project(":canimation-presets").projectDir = file("modules/canimation-presets")
project(":canimation-a11y").projectDir = file("modules/canimation-a11y")
project(":canimation-diagnostics").projectDir = file("modules/canimation-diagnostics")
project(":canimation-test").projectDir = file("modules/canimation-test")
project(":canimation-test-kit").projectDir = file("modules/canimation-test-kit")
project(":canimation-compat").projectDir = file("modules/canimation-compat")
project(":canimation-experimental").projectDir = file("modules/canimation-experimental")
project(":canimation-platform-android").projectDir = file("modules/canimation-platform-android")
project(":canimation-platform-desktop").projectDir = file("modules/canimation-platform-desktop")
project(":canimation-platform-ios").projectDir = file("modules/canimation-platform-ios")
project(":canimation-platform-web").projectDir = file("modules/canimation-platform-web")
