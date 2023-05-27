plugins {
    id("multiplatform-setup")
    kotlin("plugin.serialization") version Dependencies.Kotlin.version
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(Dependencies.Kodein.core)

                implementation(Dependencies.Kotlin.Coroutines.core)

                api(Dependencies.Ktor.core)
                implementation(Dependencies.Ktor.json)
                implementation(Dependencies.Ktor.serialization)
                implementation(Dependencies.Ktor.negotiation)
                implementation(Dependencies.Ktor.kotlin_json)
                implementation(Dependencies.Ktor.logging)
            }
        }

        desktopMain {
            dependencies {
                implementation(Dependencies.Ktor.okhttp)
            }
        }
    }
}