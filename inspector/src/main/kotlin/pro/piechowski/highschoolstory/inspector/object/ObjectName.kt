package pro.piechowski.highschoolstory.inspector.`object`

import pro.piechowski.highschoolstory.inspector.fullTypeName
import kotlin.jvm.javaPrimitiveType
import kotlin.reflect.KClass

val Any.name: String get() =
    when {
        this is String -> this
        this is Array<*> -> this.joinToString(", ", "[", "]") { it?.name ?: "null" }
        this is Iterable<*> -> this.joinToString(", ", "[", "]") { it?.name ?: "null" }
        this::class.javaPrimitiveType != null -> this.toString()
        this::class.overridesToString() -> this.toString()
        this::class.isData -> "${this::class.fullTypeName}${this.toString().substringAfter("(").let { "($it" }}"
        else -> "${this::class.fullTypeName}@${Integer.toHexString(this.hashCode())}"
    }

fun KClass<*>.overridesToString() = java.overridesToString()

fun Class<*>.overridesToString(): Boolean =
    try {
        val method = getMethod("toString") // public method (inherited or not)
        method.declaringClass != Any::class.java // not declared by java.lang.Object
    } catch (e: NoSuchMethodException) {
        false
    }
