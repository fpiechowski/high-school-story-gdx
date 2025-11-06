package pro.piechowski.highschoolstory

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

fun interface Entrypoint : KoinComponent {
    suspend fun run()
}
