plugins {
    id("canimation.platform.ios.library")
}

kotlin {
    sourceSets {
        iosMain.dependencies {
            implementation(project(":canimation-core"))
            implementation(project(":canimation-a11y"))
            implementation(project(":canimation-diagnostics"))
            implementation(libs.compose.runtime)
            implementation(libs.kotlinx.coroutinesCore)
        }
        iosTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
