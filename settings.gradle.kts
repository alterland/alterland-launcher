pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "AlterlandLauncher"

include(":desktop")

include(":common:auth")
include(":common:auth:api")
include(":common:auth:data")
include(":common:auth:presentation")
include(":common:auth:compose")

include(":common:dashboard:api")
include(":common:dashboard:data")
include(":common:dashboard:presentation")
include(":common:dashboard:compose")

include(":common:core")
include(":common:core:compose")
include(":common:core:utils")

include(":common:root")
include(":common:root:compose")
include(":common:root:core")