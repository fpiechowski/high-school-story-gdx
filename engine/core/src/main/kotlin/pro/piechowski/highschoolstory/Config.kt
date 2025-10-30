package pro.piechowski.highschoolstory

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource

data class Config(
    val debug: Boolean = false,
) {
    companion object {
        fun load() =
            ConfigLoaderBuilder
                .default()
                .addResourceSource(CONFIG_FILE)
                .build()
                .loadConfigOrThrow<Config>()

        private const val CONFIG_FILE = "/config.yml"
    }
}
