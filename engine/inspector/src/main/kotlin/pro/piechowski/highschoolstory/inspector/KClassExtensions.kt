package pro.piechowski.highschoolstory.inspector

import kotlin.reflect.KClass

val KClass<*>.fullTypeName
    get() =
        this.qualifiedName?.removePrefix(this.java.packageName + ".") ?: "Unknown"
