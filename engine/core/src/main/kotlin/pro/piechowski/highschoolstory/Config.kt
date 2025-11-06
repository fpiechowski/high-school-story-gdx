package pro.piechowski.highschoolstory

import com.sksamuel.hoplite.ConfigAlias
import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.ConfigSource
import com.sksamuel.hoplite.PropertySource
import com.sksamuel.hoplite.addResourceSource
import com.sksamuel.hoplite.sources.ConfigFilePropertySource
import org.koin.core.Koin
import pro.piechowski.highschoolstory.physics.m

data class Config(
    val debug: Debug,
    val application: Application,
    val meterCamera: MeterCamera = MeterCamera(),
) {
    companion object {
        context(koin: Koin)
        fun load() =
            ConfigLoaderBuilder
                .default()
                .addPropertySources(koin.getAll<PropertySource>())
                .build()
                .loadConfigOrThrow<Config>()
    }

    data class Debug(
        val enabled: Boolean = false,
        val server: Server = Server(),
    ) {
        data class Server(
            val enabled: Boolean = false,
            val port: Int = 8080,
        )
    }

    data class Application(
        val title: String,
        val useVsync: Boolean = true,
        val displayMode: DisplayMode,
        val resolution: Resolution,
        val refreshRate: Int,
    ) {
        enum class DisplayMode {
            WINDOWED,
            FULLSCREEN,
        }

        data class Resolution(
            val width: Int,
            val height: Int,
        )
    }

    data class MeterCamera(
        @ConfigAlias("viewportWidth") val viewportWidthMeters: Float = 23f,
        @ConfigAlias("viewportHeight") val viewportHeightMeters: Float = 13f,
    ) {
        val viewportWidth = viewportWidthMeters.m
        val viewportHeight = viewportHeightMeters.m
    }
}

fun PropertySource.Companion.resource(
    resource: String,
    optional: Boolean = false,
    allowEmpty: Boolean = false,
) = ConfigFilePropertySource(ConfigSource.ClasspathSource(resource), optional = optional, allowEmpty)
