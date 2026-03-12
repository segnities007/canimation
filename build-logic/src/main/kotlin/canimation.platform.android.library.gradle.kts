import io.github.canimation.buildlogic.canimationLibs
import io.github.canimation.buildlogic.canimationNamespace
import io.github.canimation.buildlogic.requiredVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    val libsCatalog = project.canimationLibs()

    androidLibrary {
        namespace = project.canimationNamespace()
        compileSdk = libsCatalog.requiredVersion("android-compileSdk").toInt()
        minSdk = libsCatalog.requiredVersion("android-minSdk").toInt()
        withHostTestBuilder {}.configure {}
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
}
