plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:core:utils"))

                implementation(project(":common:auth"))
                implementation(project(":common:dashboard"))

                implementation(Dependencies.Kodein.core)
            }
        }
    }
}