plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:dashboard:api"))
                implementation(project(":common:core"))
                implementation(project(":common:core:utils"))
            }
        }
    }
}