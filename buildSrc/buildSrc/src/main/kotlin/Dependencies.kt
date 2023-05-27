object Dependencies {

    object Kodein {
        private const val version = "7.20.1"

        const val core = "org.kodein.di:kodein-di:$version"
        const val compose = "org.kodein.di:kodein-di-framework-compose:$version"
    }

    object Kotlin {
        const val version = "1.8.20"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Serialization {
            private const val jsonSerializationVersion = "1.5.1"
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:$version"
            const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$jsonSerializationVersion"
        }

        object Coroutines {
            private const val version = "1.7.1"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val swing = "org.jetbrains.kotlinx:kotlinx-coroutines-swing:$version"
        }
    }

    object Compose {
        private const val version = "1.4.0"
        const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$version"
    }

    object Ktor {
        private const val version = "2.3.0"
        const val core = "io.ktor:ktor-client-core:$version"
        const val json = "io.ktor:ktor-client-json:$version"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val kotlin_json = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val serialization = "io.ktor:ktor-client-serialization:$version"
        const val logging = "io.ktor:ktor-client-logging:$version"
        const val okhttp = "io.ktor:ktor-client-okhttp:$version"
    }

    object Voyager {
        private const val version = "1.0.0-rc06"

        const val core = "cafe.adriel.voyager:voyager-core:$version"
        const val navigator = "cafe.adriel.voyager:voyager-navigator:$version"
        const val transitions = "cafe.adriel.voyager:voyager-transitions:$version"
        const val kodein = "cafe.adriel.voyager:voyager-kodein:$version"
    }

    object Image {
        object Coil {
            private const val version = "2.3.0"
            const val core = "io.coil-kt:coil:$version"
            const val compose = "io.coil-kt:coil-compose:$version"
        }
    }
}