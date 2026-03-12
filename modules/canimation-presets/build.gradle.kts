plugins {
    id("canimation.compose.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":canimation-core"))
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.compose.animation)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(project(":canimation-recipes"))
        }
    }
}
