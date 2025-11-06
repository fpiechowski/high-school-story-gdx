plugins {
    java
    application
    kotlin("jvm")
    alias(libs.plugins.javafx)
    alias(libs.plugins.jlink)
    alias(libs.plugins.hex)
}

group = "pro.piechowski.highschoolstory"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion = "5.12.1"

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainClass.set("pro.piechowski.highschoolstory.inspector.InspectorLauncherKt")
    applicationDefaultJvmArgs = listOf("-XX:+AllowEnhancedClassRedefinition", "-XX:HotswapAgent=fatjar")
}

kotlin {
    jvmToolchain(17)
}

javafx {
    version = "21.0.6"
    modules = listOf("javafx.controls", "javafx.fxml")
}

tasks.compileDomainJava

dependencies {

    domainImplementation(libs.bundles.kotlin)
    domainImplementation(project(":engine:inspector:runtime"))

    implementation(libs.bundles.kotlin)

    implementation(project(":game:core"))
    implementation(project(":game:lwjgl3"))
    implementation(project(":engine:inspector:runtime"))
    implementation(libs.kotlinx.coroutines.javafx)
    implementation(libs.classgraph)

    implementation(libs.draculafx)

    implementation(libs.ikonli.javafx)
    implementation(libs.ikonli.fontawesome)

    testImplementation(libs.junit)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jlink {
    imageZip.set(layout.buildDirectory.file("/distributions/app-${javafx.platform.classifier}.zip"))
    options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
    launcher {
        name = "app"
    }
}

val openAllModulesArgs =
    listOf(
        "--add-opens",
        "java.base/java.lang=ALL-UNNAMED",
        "--add-opens",
        "java.base/java.lang.invoke=ALL-UNNAMED",
        "--add-opens",
        "java.base/java.lang.reflect=ALL-UNNAMED",
        "--add-opens",
        "java.base/java.util=ALL-UNNAMED",
        "--add-opens",
        "java.base/java.time=ALL-UNNAMED",
        "--add-opens",
        "java.base/java.io=ALL-UNNAMED",
        "--add-opens",
        "java.base/java.net=ALL-UNNAMED",
        "--add-opens",
        "java.base/java.math=ALL-UNNAMED",
        "--add-opens",
        "java.base/java.text=ALL-UNNAMED",
        "--add-opens",
        "java.desktop/java.awt=ALL-UNNAMED",
        "--add-opens",
        "java.desktop/sun.awt=ALL-UNNAMED",
        "--add-opens",
        "java.sql/java.sql=ALL-UNNAMED",
        "--add-opens",
        "javafx.base/com.sun.javafx.property.adapter=ALL-UNNAMED",
        "--add-opens",
        "javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED",
        "--add-opens",
        "javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED",
    )

tasks.withType<JavaExec>().configureEach {
    jvmArgs(openAllModulesArgs)
}

tasks.matching { it.name == "jlink" || it.name == "run" }.configureEach {
    if (this is JavaExec) jvmArgs(openAllModulesArgs)
}
