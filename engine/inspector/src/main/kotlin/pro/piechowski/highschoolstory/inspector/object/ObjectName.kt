package pro.piechowski.highschoolstory.inspector.`object`

import pro.piechowski.highschoolstory.inspector.fullTypeName
import pro.piechowski.highschoolstory.inspector.overridesToString

val Any.name: String
    get() =
        when {
            this is String -> this
            this is Array<*> -> this.joinToString(", ", "[", "]") { it?.name ?: "null" }
            this is Iterable<*> -> this.joinToString(", ", "[", "]") { it?.name ?: "null" }
            this::class.javaPrimitiveType != null -> this.toString()
            this::class.overridesToString() -> this.toString()
            this::class.isData -> "${this::class.fullTypeName}${this.toString().substringAfter("(").let { "($it" }}"
            else -> "${this::class.fullTypeName}@${Integer.toHexString(this.hashCode())}"
        }
