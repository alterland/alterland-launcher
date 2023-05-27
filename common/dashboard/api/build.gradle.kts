plugins {
    id("multiplatform-setup")
    kotlin("plugin.serialization") version Dependencies.Kotlin.version
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Dependencies.Kotlin.Coroutines.core)
                implementation(Dependencies.Kotlin.Serialization.serialization)
            }
        }
    }
}