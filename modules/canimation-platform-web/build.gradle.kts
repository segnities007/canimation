plugins {
    id("canimation.platform.web.library")
}

kotlin {
    sourceSets {
        jsMain.dependencies {
            implementation(project(":canimation-core"))
            implementation(project(":canimation-a11y"))
            implementation(project(":canimation-diagnostics"))
            implementation(libs.compose.runtime)
            implementation(libs.kotlinx.coroutinesCore)
        }
        jsTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutinesCore)
            implementation(libs.kotlinx.coroutinesTest)
        }
    }
}
