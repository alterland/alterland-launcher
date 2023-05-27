plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:auth:data"))
                implementation(project(":common:auth:presentation"))

                implementation(Dependencies.Kodein.core)
            }
        }
    }
}