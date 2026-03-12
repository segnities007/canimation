import io.github.canimation.buildlogic.canimationFrameworkBaseName
import io.github.canimation.buildlogic.canimationLibs
import io.github.canimation.buildlogic.canimationNamespace
import io.github.canimation.buildlogic.requiredVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    val libsCatalog = project.canimationLibs()

    androidLibrary {
        namespace = project.canimationNamespace()
        compileSdk = libsCatalog.requiredVersion("android-compileSdk").toInt()
        minSdk = libsCatalog.requiredVersion("android-minSdk").toInt()
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = project.canimationFrameworkBaseName()
            isStatic = true
        }
    }

    jvm()

    js {
        browser()
    }

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }
}
