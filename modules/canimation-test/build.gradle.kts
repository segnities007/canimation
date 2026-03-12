plugins {
    id("canimation.compose.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":canimation-core"))
            api(project(":canimation-recipes"))
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
