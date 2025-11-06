package pro.piechowski.highschoolstory.inspector.container

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import pro.piechowski.highschoolstory.inspector.fullTypeName
import pro.piechowski.highschoolstory.inspector.tickerFlow
import kotlin.time.Duration.Companion.seconds

@ExperimentalCoroutinesApi
class ObjectContainerViewModel(
    private val objectContainer: ObjectContainer,
) {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val instances =
        tickerFlow(2.seconds)
            .flatMapLatest { objectContainer.objects }

    val searchText = MutableStateFlow("")

    val filteredInstances =
        instances.combine(searchText) { instances, searchText ->
            instances.filter {
                it.type.fullTypeName.contains(searchText, ignoreCase = true) ||
                    it.instance.toString().contains(searchText, ignoreCase = true)
            }
        }
}
