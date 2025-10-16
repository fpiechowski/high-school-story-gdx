package pro.piechowski.highschoolstory.inspector

import kotlin.reflect.KFunction
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

fun <T : Any, R> T.propertyValue(name: String): R =
    (
        this::class.memberProperties.find {
            it.name == name
        } as KProperty1<T, R>
    ).get(this)

fun <T : Any, R> T.functionResult(
    name: String,
    vararg args: Any?,
): R =
    (
        this::class.memberFunctions.find {
            it.name == name
        } as KFunction<R>
    ).call(args)
