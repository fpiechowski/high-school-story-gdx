package pro.piechowski.highschoolstory.inspector.koin

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.instance.SingleInstanceFactory
import pro.piechowski.highschoolstory.inspector.InspectorViewModel
import pro.piechowski.highschoolstory.inspector.fullTypeName
import pro.piechowski.highschoolstory.inspector.globals.GlobalInstance
import pro.piechowski.highschoolstory.inspector.globals.GlobalInstances
import pro.piechowski.highschoolstory.inspector.tickerFlow
import kotlin.time.Duration.Companion.seconds

@ExperimentalCoroutinesApi
@KoinInternalApi
class GlobalInstancesViewModel(
    private val globalInstances: GlobalInstances,
) : InspectorViewModel() {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val instances =
        tickerFlow(2.seconds)
            .flatMapLatest { globalInstances.instances }

    val searchText = MutableStateFlow("")

    val filteredInstances =
        instances.combine(searchText) { instances, searchText ->
            instances.filter {
                it.type.fullTypeName.contains(searchText, ignoreCase = true) ||
                    it.value.toString().contains(searchText, ignoreCase = true)
            }
        }
}
