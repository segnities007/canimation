package io.github.canimation.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

private val namespaceOverrides =
    mapOf(
        "canimation-platform-android" to "io.github.canimation.platform.android",
        "canimation-platform-desktop" to "io.github.canimation.platform.desktop",
        "canimation-platform-ios" to "io.github.canimation.platform.ios",
        "canimation-platform-web" to "io.github.canimation.platform.web",
        "canimation-test-kit" to "io.github.canimation.testkit",
    )

internal fun Project.canimationNamespace(): String =
    namespaceOverrides[name]
        ?: "io.github.canimation.${name.removePrefix("canimation-")}"

internal fun Project.canimationFrameworkBaseName(): String =
    name.split('-').joinToString(separator = "") { segment ->
        segment.replaceFirstChar { character -> character.uppercase() }
    }

internal fun Project.canimationLibs(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.requiredVersion(alias: String): String = findVersion(alias).get().requiredVersion
