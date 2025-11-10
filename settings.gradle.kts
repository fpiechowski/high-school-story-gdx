plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

includeBuild("./engine")

include(":game:core", ":game:lwjgl3")
