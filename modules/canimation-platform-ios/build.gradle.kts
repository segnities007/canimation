plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "CanimationPlatformIos"
            isStatic = true
        }
    }

    sourceSets {
        iosMain.dependencies {
            implementation(project(":canimation-core"))
            implementation(project(":canimation-a11y"))
            implementation(project(":canimation-diagnostics"))
            implementation(libs.compose.runtime)
            implementation(libs.kotlinx.coroutinesCore)
        }
        iosTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
