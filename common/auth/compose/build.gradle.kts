plugins {
    id("multiplatform-compose-setup")
}


kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:auth:presentation"))
                implementation(project(":common:core:compose"))
                implementation(project(":common:core:utils"))

                implementation(Dependencies.Voyager.core)
                implementation(Dependencies.Voyager.navigator)
                implementation(Dependencies.Voyager.transitions)
                implementation(Dependencies.Voyager.kodein)
            }
        }
    }
}