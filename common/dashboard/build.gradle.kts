plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:dashboard:data"))
                implementation(project(":common:dashboard:presentation"))

                implementation(Dependencies.Kodein.core)
            }
        }
    }
}