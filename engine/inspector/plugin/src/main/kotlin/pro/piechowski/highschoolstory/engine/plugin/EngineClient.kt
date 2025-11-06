package pro.piechowski.highschoolstory.engine.plugin

import kotlinx.serialization.json.Json
import pro.piechowski.highschoolstory.debug.server.KoinObjects
import java.net.HttpURLConnection
import java.net.URL

object EngineClient {
    private val json = Json { ignoreUnknownKeys = true }

    fun getKoinObjects(baseUrl: String): KoinObjects {
        val text = httpGet("$baseUrl/koin")
        return json.decodeFromString(KoinObjects.serializer(), text)
    }

    private fun httpGet(url: String): String {
        val conn = URL(url).openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.connectTimeout = 2_000
        conn.readTimeout = 4_000
        conn.inputStream.bufferedReader().use { return it.readText() }
    }
}
