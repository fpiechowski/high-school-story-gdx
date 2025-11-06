package pro.piechowski.highschoolstory.debug.server

import kotlin.reflect.KClass

val KClass<*>.fullTypeName get() =
    this.qualifiedName?.removePrefix(this.java.packageName + ".") ?: "Unknown"
