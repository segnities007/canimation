import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.tasks.PublishToMavenRepository
import org.gradle.jvm.tasks.Jar
import org.gradle.plugins.signing.Sign
import org.gradle.plugins.signing.SigningExtension
import org.jetbrains.dokka.gradle.DokkaExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.abi.AbiValidationMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidKotlinMultiplatformLibrary) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.dokka)
    alias(libs.plugins.dokkaJavadoc) apply false
}

data class PublishedModuleMetadata(
    val displayName: String,
    val description: String,
)

fun Project.moduleSourcePath(): String = projectDir.relativeTo(rootDir).invariantSeparatorsPath

val publishableProjects =
    listOf(
        project(":canimation-core"),
        project(":canimation-tokens"),
        project(":canimation-primitives"),
        project(":canimation-semantics"),
        project(":canimation-recipes"),
        project(":canimation-runtime"),
        project(":canimation-presets"),
        project(":canimation-a11y"),
        project(":canimation-diagnostics"),
        project(":canimation-test"),
        project(":canimation-test-kit"),
        project(":canimation-compat"),
        project(":canimation-experimental"),
        project(":canimation-platform-android"),
        project(":canimation-platform-desktop"),
        project(":canimation-platform-ios"),
        project(":canimation-platform-web"),
    )

val moduleMetadataByPath =
    mapOf(
        ":canimation-core" to
            PublishedModuleMetadata(
                displayName = "canimation-core",
                description = "Core runtime APIs, provider state, and Compose modifier entry points for Canimation.",
            ),
        ":canimation-tokens" to
            PublishedModuleMetadata(
                displayName = "canimation-tokens",
                description = "Animation token source of truth for durations, easing, distance, and spring values.",
            ),
        ":canimation-primitives" to
            PublishedModuleMetadata(
                displayName = "canimation-primitives",
                description = "Primitive motion source of truth for effects, specs, and policy-level motion values.",
            ),
        ":canimation-semantics" to
            PublishedModuleMetadata(
                displayName = "canimation-semantics",
                description = "Semantic recipe source of truth for descriptor contracts, taxonomy, and registry behavior.",
            ),
        ":canimation-recipes" to
            PublishedModuleMetadata(
                displayName = "canimation-recipes",
                description = "Target-state first-party semantic recipe catalog and recipe access layer.",
            ),
        ":canimation-runtime" to
            PublishedModuleMetadata(
                displayName = "canimation-runtime",
                description = "Target-state runtime API for provider, modifier, visibility, and transitions.",
            ),
        ":canimation-presets" to
            PublishedModuleMetadata(
                displayName = "canimation-presets",
                description = "Preset registry and curated animation defaults for Canimation.",
            ),
        ":canimation-a11y" to
            PublishedModuleMetadata(
                displayName = "canimation-a11y",
                description = "Accessibility and reduced-motion policy helpers for Canimation.",
            ),
        ":canimation-diagnostics" to
            PublishedModuleMetadata(
                displayName = "canimation-diagnostics",
                description = "Diagnostics overlays and frame-metrics helpers for Canimation.",
            ),
        ":canimation-test" to
            PublishedModuleMetadata(
                displayName = "canimation-test",
                description = "Test hosts and motion-testing utilities for Canimation consumers.",
            ),
        ":canimation-test-kit" to
            PublishedModuleMetadata(
                displayName = "canimation-test-kit",
                description = "Target-state testing surface for deterministic Canimation runtime tests.",
            ),
        ":canimation-compat" to
            PublishedModuleMetadata(
                displayName = "canimation-compat",
                description = "Compatibility layer exposing effect-first and preset-first migration APIs.",
            ),
        ":canimation-experimental" to
            PublishedModuleMetadata(
                displayName = "canimation-experimental",
                description = "Opt-in experimental motion surfaces isolated from the stable semantic API.",
            ),
        ":canimation-platform-android" to
            PublishedModuleMetadata(
                displayName = "canimation-platform-android",
                description = "Android platform adapters for system motion preference and diagnostics.",
            ),
        ":canimation-platform-desktop" to
            PublishedModuleMetadata(
                displayName = "canimation-platform-desktop",
                description = "Desktop JVM adapters for system motion preference and diagnostics.",
            ),
        ":canimation-platform-ios" to
            PublishedModuleMetadata(
                displayName = "canimation-platform-ios",
                description = "iOS adapters for system motion preference and diagnostics.",
            ),
        ":canimation-platform-web" to
            PublishedModuleMetadata(
                displayName = "canimation-platform-web",
                description = "Web JS adapters for system motion preference and diagnostics.",
            ),
    )

group = providers.gradleProperty("group").get()
version = providers.gradleProperty("version").get()

val remotePublishRequested =
    gradle.startParameter.taskNames.any { taskName ->
        taskName.contains("publish", ignoreCase = true) &&
            !taskName.contains("MavenLocal", ignoreCase = true)
    }

val verifyRemotePublishingEnvironment =
    tasks.register("verifyRemotePublishingEnvironment") {
        onlyIf { remotePublishRequested }
        doLast {
            val requiredVariables =
                listOf(
                    "CANIMATION_MAVEN_URL",
                    "CANIMATION_MAVEN_USERNAME",
                    "CANIMATION_MAVEN_PASSWORD",
                    "CANIMATION_SIGNING_KEY",
                    "CANIMATION_SIGNING_PASSWORD",
                    "CANIMATION_SIGNING_KEY_ID",
                )
            val missingVariables =
                requiredVariables.filter { name ->
                    providers.environmentVariable(name).orNull.isNullOrBlank()
                }
            check(missingVariables.isEmpty()) {
                "Missing release environment variables: ${missingVariables.joinToString()}"
            }
        }
    }

val updateLibraryAbi =
    tasks.register("updateLibraryAbi") {
        dependsOn(publishableProjects.map { "${it.path}:updateLegacyAbi" })
    }

val checkLibraryAbi =
    tasks.register("checkLibraryAbi") {
        dependsOn(publishableProjects.map { "${it.path}:checkLegacyAbi" })
    }

val publishLibraryToMavenLocal =
    tasks.register("publishLibraryToMavenLocal") {
        dependsOn(publishableProjects.map { "${it.path}:publishToMavenLocal" })
    }

tasks.register("releaseReadiness") {
    dependsOn(
        checkLibraryAbi,
        "dokkaGenerate",
        publishLibraryToMavenLocal,
    )
}

dependencies {
    publishableProjects.forEach { project ->
        add("dokka", project)
    }
}

dokka {
    moduleName.set("Canimation")
    dokkaPublications.named("html") {
        outputDirectory.set(layout.buildDirectory.dir("dokka/html"))
        includes.from("README.md")
    }
}

configure(publishableProjects) {
    val moduleMetadata = moduleMetadataByPath.getValue(path)

    group = rootProject.group
    version = rootProject.version

    apply(plugin = "maven-publish")
    apply(plugin = "signing")
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "org.jetbrains.dokka-javadoc")

    pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
        extensions.configure<KotlinMultiplatformExtension> {
            @OptIn(ExperimentalAbiValidation::class)
            (this as ExtensionAware).extensions.configure<AbiValidationMultiplatformExtension>("abiValidation") {
                enabled.set(true)
            }
        }
    }

    extensions.configure<DokkaExtension> {
        moduleName.set(moduleMetadata.displayName)
        dokkaPublications.named("html") {
            outputDirectory.set(layout.buildDirectory.dir("dokka/html"))
        }
        dokkaPublications.named("javadoc") {
            outputDirectory.set(layout.buildDirectory.dir("dokka/javadoc"))
        }
        dokkaSourceSets.configureEach {
            sourceLink {
                localDirectory.set(project.file("src"))
                remoteUrl("https://github.com/segnities007/canimation/tree/main/${project.moduleSourcePath()}/src")
                remoteLineSuffix.set("#L")
            }
        }
    }

    val dokkaJavadocJar =
        tasks.register("dokkaJavadocJar", Jar::class) {
            description = "Packages Dokka Javadoc output for ${project.path}."
            archiveClassifier.set("javadoc")
            dependsOn(tasks.named("dokkaGeneratePublicationJavadoc"))
            from(layout.buildDirectory.dir("dokka/javadoc"))
        }

    extensions.configure<PublishingExtension> {
        repositories {
            val mavenUrl = providers.environmentVariable("CANIMATION_MAVEN_URL").orNull
            if (!mavenUrl.isNullOrBlank()) {
                maven {
                    name = "release"
                    url = uri(mavenUrl)
                    credentials {
                        username = providers.environmentVariable("CANIMATION_MAVEN_USERNAME").orNull
                        password = providers.environmentVariable("CANIMATION_MAVEN_PASSWORD").orNull
                    }
                }
            }
        }

        publications.withType(MavenPublication::class.java).configureEach {
            artifact(dokkaJavadocJar)
            pom {
                name.set(moduleMetadata.displayName)
                description.set(moduleMetadata.description)
                url.set("https://github.com/segnities007/canimation")
                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("segnities007")
                        name.set("segnities007")
                        url.set("https://github.com/segnities007")
                    }
                }
                scm {
                    url.set("https://github.com/segnities007/canimation")
                    connection.set("scm:git:https://github.com/segnities007/canimation.git")
                    developerConnection.set("scm:git:ssh://git@github.com/segnities007/canimation.git")
                }
            }
        }
    }

    extensions.configure<SigningExtension> {
        isRequired = remotePublishRequested

        val signingKey = providers.environmentVariable("CANIMATION_SIGNING_KEY").orNull
        val signingPassword = providers.environmentVariable("CANIMATION_SIGNING_PASSWORD").orNull
        val signingKeyId = providers.environmentVariable("CANIMATION_SIGNING_KEY_ID").orNull

        if (!signingKey.isNullOrBlank() && !signingPassword.isNullOrBlank()) {
            useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
        }

        sign(extensions.getByType(PublishingExtension::class.java).publications)
    }

    tasks.withType(PublishToMavenRepository::class.java).configureEach {
        dependsOn(rootProject.tasks.named("verifyRemotePublishingEnvironment"))
    }
    tasks.withType(Sign::class.java).configureEach {
        dependsOn(rootProject.tasks.named("verifyRemotePublishingEnvironment"))
    }
}
