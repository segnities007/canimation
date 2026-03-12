import io.github.canimation.buildlogic.canimationFrameworkBaseName

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = project.canimationFrameworkBaseName()
            isStatic = true
        }
    }
}
