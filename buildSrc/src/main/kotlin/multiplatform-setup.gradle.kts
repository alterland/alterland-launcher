plugins {
    kotlin("multiplatform")
    kotlin("kapt")
}

kotlin {
    jvm("desktop")

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}