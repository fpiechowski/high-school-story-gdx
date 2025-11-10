import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

plugins {
    kotlin("jvm")
}

subprojects {
    plugins.withType<KotlinPluginWrapper> {
        dependencies {
            implementation(project(":engine:core"))
        }
    }
}
