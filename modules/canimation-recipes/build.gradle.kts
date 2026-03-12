plugins {
    id("canimation.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":canimation-primitives"))
            api(project(":canimation-semantics"))
            api(project(":canimation-presets"))
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
