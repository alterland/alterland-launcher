plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(Dependencies.Kotlin.Coroutines.core)
                api(Dependencies.Kotlin.Coroutines.swing)
                api(Dependencies.Voyager.core)
            }
        }
    }
}