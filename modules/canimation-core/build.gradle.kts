plugins {
    id("canimation.compose.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":canimation-semantics"))
            api(project(":canimation-primitives"))
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.animation)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.compose.animation)
            implementation(libs.compose.ui)
            implementation(project(":canimation-recipes"))
        }
    }
}
