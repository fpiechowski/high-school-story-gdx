package pro.piechowski.highschoolstory

import org.koin.core.Koin
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

context(koin: Koin)
fun <T> get(
    qualifier: Qualifier? = null,
    parameters: ParametersDefinition? = null,
): T =
    with(koin) {
        get(qualifier, parameters)
    }

context(koin: Koin)
fun <T> inject(
    qualifier: Qualifier? = null,
    parameters: ParametersDefinition? = null,
): T =
    with(koin) {
        inject(qualifier, parameters)
    }
