plugins {
    id("canimation.compose.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":canimation-core"))
            api(project(":canimation-recipes"))
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
