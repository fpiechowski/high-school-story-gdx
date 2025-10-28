package pro.piechowski.highschoolstory.inspector

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.annotation.KoinInternalApi
import pro.piechowski.highschoolstory.inspector.container.KoinObjectContainer
import pro.piechowski.highschoolstory.inspector.ecs.FleksECS
import pro.piechowski.highschoolstory.inspector.ecs.GlobalFleksWorldInstanceProvider
import pro.piechowski.highschoolstory.inspector.runtime.LibGDXRuntime

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
@KoinInternalApi
class Adapters {
    val runtime = LibGDXRuntime()

    private val koinGlobalInstances = KoinObjectContainer()
    val globalInstances = koinGlobalInstances

    private val globalFleksWorldInstanceProvider = GlobalFleksWorldInstanceProvider(globalInstances)
    val ecs = FleksECS(globalFleksWorldInstanceProvider.world)
}
