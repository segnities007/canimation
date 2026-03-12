plugins {
    id("canimation.compose.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":canimation-core"))
            api(project(":canimation-recipes"))
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.animation)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
