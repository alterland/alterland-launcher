plugins {
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:core:compose"))
                implementation(project(":common:core:utils"))

                implementation(project(":common:auth:data"))
                implementation(project(":common:auth:compose"))

                implementation(project(":common:dashboard:data"))
                implementation(project(":common:dashboard:compose"))

                implementation(Dependencies.Voyager.navigator)
                implementation(Dependencies.Voyager.transitions)
                implementation(Dependencies.Voyager.kodein)

                implementation(Dependencies.Kodein.compose)
            }
        }
    }
}