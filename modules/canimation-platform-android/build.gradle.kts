import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidLibrary {
        namespace = "io.github.canimation.platform.android"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(project(":canimation-core"))
            implementation(project(":canimation-a11y"))
            implementation(project(":canimation-diagnostics"))
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.androidx.core.ktx)
            implementation(libs.kotlinx.coroutinesCore)
        }
    }
}
