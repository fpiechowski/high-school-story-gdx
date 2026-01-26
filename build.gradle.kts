import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    idea
    eclipse
    kotlin("jvm") version "2.2.20"
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ksp)
}

allprojects {
    apply(plugin = "eclipse")
    apply(plugin = "idea")

    idea {
        module {
            outputDir = file("build/classes/java/main")
            testOutputDir = file("build/classes/java/test")
        }
    }

    repositories {
        mavenCentral()
        maven { url = uri("https://s01.oss.sonatype.org") }
        gradlePluginPortal()
        mavenLocal()
        google()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/") }
        maven { url = uri("https://maven.scijava.org/content/repositories/public/") }
        maven { url = uri("https://raw.githubusercontent.com/kotlin-graphics/mary/master") }
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        tasks {
            compileKotlin {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
            }

            compileTestKotlin {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
            }

            test {
                useJUnitPlatform()
            }
        }

        kotlin {
            jvmToolchain(17)
            compilerOptions {
                freeCompilerArgs.add("-Xcontext-parameters")
                jvmTarget.set(JvmTarget.JVM_17)
            }

            sourceSets.all {
                listOf(
                    "kotlin.time.ExperimentalTime",
                    "kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "org.koin.core.annotation.KoinInternalApi",
                    "kotlin.ExperimentalUnsignedTypes",
                    "kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "kotlin.ExperimentalContextParameters",
                    "arrow.fx.coroutines.await.ExperimentalAwaitAllApi"
                ).forEach {
                    languageSettings.optIn(it)
                }
            }
        }
    }

    plugins.withId("java") {
        tasks.compileJava {
            options.setIncremental(true)
            options.encoding = "UTF-8"
        }

        tasks.compileTestJava {
            options.encoding = "UTF-8"
        }

        java {
            toolchain.languageVersion.set(JavaLanguageVersion.of(17))
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }


    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    java {
        sourceCompatibility = JavaVersion.VERSION_17
    }

    val generateAssetList by tasks.registering {
        inputs.dir("${project.projectDir}/assets/")
        val assetsFolder = File("${project.projectDir}/assets/")
        val assetsFile = File(assetsFolder, "assets.txt")
        assetsFile.delete()

        fileTree(assetsFolder).map { it.relativeTo(assetsFolder) }.sorted().forEach {
            assetsFile.appendText(it.path + "\n")
        }
    }

    tasks {
        processResources {
            dependsOn(generateAssetList)
        }
    }

    val projectVersion: String by project

    version = projectVersion
    ext["appName"] = "HighSchoolStory"
}
