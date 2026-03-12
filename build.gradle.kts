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

data class PublishedModuleVerification(
    val androidCompileTask: String? = null,
    val jvmCompileTask: String? = null,
    val appleCompileTask: String? = null,
    val webCompileTasks: List<String> = emptyList(),
)

data class PublishedModuleDefinition(
    val path: String,
    val metadata: PublishedModuleMetadata,
    val verification: PublishedModuleVerification,
)

fun Project.moduleSourcePath(): String = projectDir.relativeTo(rootDir).invariantSeparatorsPath

val publishedModules =
    listOf(
        PublishedModuleDefinition(
            path = ":canimation-core",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-core",
                    description = "Core runtime APIs, provider state, and Compose modifier entry points for Canimation.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-core:compileAndroidMain",
                    jvmCompileTask = ":canimation-core:compileKotlinJvm",
                    appleCompileTask = ":canimation-core:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-core:compileKotlinJs", ":canimation-core:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-tokens",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-tokens",
                    description = "Animation token source of truth for durations, easing, distance, and spring values.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-tokens:compileAndroidMain",
                    jvmCompileTask = ":canimation-tokens:compileKotlinJvm",
                    appleCompileTask = ":canimation-tokens:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-tokens:compileKotlinJs", ":canimation-tokens:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-primitives",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-primitives",
                    description = "Primitive motion source of truth for effects, specs, and policy-level motion values.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-primitives:compileAndroidMain",
                    jvmCompileTask = ":canimation-primitives:compileKotlinJvm",
                    appleCompileTask = ":canimation-primitives:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-primitives:compileKotlinJs", ":canimation-primitives:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-semantics",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-semantics",
                    description = "Semantic recipe source of truth for descriptor contracts, taxonomy, and registry behavior.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-semantics:compileAndroidMain",
                    jvmCompileTask = ":canimation-semantics:compileKotlinJvm",
                    appleCompileTask = ":canimation-semantics:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-semantics:compileKotlinJs", ":canimation-semantics:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-recipes",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-recipes",
                    description = "Target-state first-party semantic recipe catalog and recipe access layer.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-recipes:compileAndroidMain",
                    jvmCompileTask = ":canimation-recipes:compileKotlinJvm",
                    appleCompileTask = ":canimation-recipes:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-recipes:compileKotlinJs", ":canimation-recipes:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-runtime",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-runtime",
                    description = "Target-state runtime API for provider, modifier, visibility, and transitions.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-runtime:compileAndroidMain",
                    jvmCompileTask = ":canimation-runtime:compileKotlinJvm",
                    appleCompileTask = ":canimation-runtime:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-runtime:compileKotlinJs", ":canimation-runtime:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-presets",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-presets",
                    description = "Preset registry and curated animation defaults for Canimation.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-presets:compileAndroidMain",
                    jvmCompileTask = ":canimation-presets:compileKotlinJvm",
                    appleCompileTask = ":canimation-presets:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-presets:compileKotlinJs", ":canimation-presets:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-a11y",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-a11y",
                    description = "Accessibility and reduced-motion policy helpers for Canimation.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-a11y:compileAndroidMain",
                    jvmCompileTask = ":canimation-a11y:compileKotlinJvm",
                    appleCompileTask = ":canimation-a11y:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-a11y:compileKotlinJs", ":canimation-a11y:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-diagnostics",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-diagnostics",
                    description = "Diagnostics overlays and frame-metrics helpers for Canimation.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-diagnostics:compileAndroidMain",
                    jvmCompileTask = ":canimation-diagnostics:compileKotlinJvm",
                    appleCompileTask = ":canimation-diagnostics:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-diagnostics:compileKotlinJs", ":canimation-diagnostics:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-test",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-test",
                    description = "Test hosts and motion-testing utilities for Canimation consumers.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-test:compileAndroidMain",
                    jvmCompileTask = ":canimation-test:compileKotlinJvm",
                    appleCompileTask = ":canimation-test:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-test:compileKotlinJs", ":canimation-test:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-test-kit",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-test-kit",
                    description = "Target-state testing surface for deterministic Canimation runtime tests.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-test-kit:compileAndroidMain",
                    jvmCompileTask = ":canimation-test-kit:compileKotlinJvm",
                    appleCompileTask = ":canimation-test-kit:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-test-kit:compileKotlinJs", ":canimation-test-kit:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-compat",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-compat",
                    description = "Compatibility layer exposing effect-first and preset-first migration APIs.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-compat:compileAndroidMain",
                    jvmCompileTask = ":canimation-compat:compileKotlinJvm",
                    appleCompileTask = ":canimation-compat:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-compat:compileKotlinJs", ":canimation-compat:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-experimental",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-experimental",
                    description = "Opt-in experimental motion surfaces isolated from the stable semantic API.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-experimental:compileAndroidMain",
                    jvmCompileTask = ":canimation-experimental:compileKotlinJvm",
                    appleCompileTask = ":canimation-experimental:compileKotlinIosSimulatorArm64",
                    webCompileTasks = listOf(":canimation-experimental:compileKotlinJs", ":canimation-experimental:compileKotlinWasmJs"),
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-platform-android",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-platform-android",
                    description = "Android platform adapters for system motion preference and diagnostics.",
                ),
            verification =
                PublishedModuleVerification(
                    androidCompileTask = ":canimation-platform-android:compileAndroidMain",
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-platform-desktop",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-platform-desktop",
                    description = "Desktop JVM adapters for system motion preference and diagnostics.",
                ),
            verification =
                PublishedModuleVerification(
                    jvmCompileTask = ":canimation-platform-desktop:compileKotlinJvm",
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-platform-ios",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-platform-ios",
                    description = "iOS adapters for system motion preference and diagnostics.",
                ),
            verification =
                PublishedModuleVerification(
                    appleCompileTask = ":canimation-platform-ios:compileKotlinIosSimulatorArm64",
                ),
        ),
        PublishedModuleDefinition(
            path = ":canimation-platform-web",
            metadata =
                PublishedModuleMetadata(
                    displayName = "canimation-platform-web",
                    description = "Web JS adapters for system motion preference and diagnostics.",
                ),
            verification =
                PublishedModuleVerification(
                    webCompileTasks = listOf(":canimation-platform-web:compileKotlinJs"),
                ),
        ),
    )

val publishableProjects = publishedModules.map { module -> project(module.path) }

val moduleMetadataByPath =
    publishedModules.associate { module ->
        module.path to module.metadata
    }

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

val compileLibraryAndroid =
    tasks.register("compileLibraryAndroid") {
        description = "Compiles Android library modules."
        dependsOn(publishedModules.mapNotNull { module -> module.verification.androidCompileTask })
    }

val compileLibraryJvm =
    tasks.register("compileLibraryJvm") {
        description = "Compiles JVM library modules."
        dependsOn(publishedModules.mapNotNull { module -> module.verification.jvmCompileTask })
    }

val compileLibraryApple =
    tasks.register("compileLibraryApple") {
        description = "Compiles Apple library modules."
        dependsOn(publishedModules.mapNotNull { module -> module.verification.appleCompileTask })
    }

val compileLibraryWeb =
    tasks.register("compileLibraryWeb") {
        description = "Compiles JS and Wasm library modules."
        dependsOn(publishedModules.flatMap { module -> module.verification.webCompileTasks })
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
