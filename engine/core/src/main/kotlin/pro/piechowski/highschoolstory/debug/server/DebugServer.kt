package pro.piechowski.highschoolstory.debug.server

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.Config

@ExperimentalContextParameters
@ExperimentalCoroutinesApi
@KoinInternalApi
class DebugServer : KoinComponent {
    private val config by inject<Config>()

    private val koinObjectsProvider by inject<KoinObjectsProvider>()
    private val fleksEntityComponentsProvider by inject<FleksEntityComponentsProvider>()

    private val embeddedServer =
        embeddedServer(Netty, config.debug.server.port) {
            routing {
                get("/koin") {
                    call.respond(koinObjectsProvider.objects)
                }
                get("/fleks") {
                    call.respond(fleksEntityComponentsProvider.entityComponents)
                }
            }
        }

    init {
        if (config.debug.server.enabled) {
            embeddedServer.start()
        }
    }
}
