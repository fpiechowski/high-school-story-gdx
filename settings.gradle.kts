plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

include(":game:core", ":game:lwjgl3")
include(":engine:inspector", ":engine:inspector:runtime", ":engine:core", ":engine:inspector:plugin")
