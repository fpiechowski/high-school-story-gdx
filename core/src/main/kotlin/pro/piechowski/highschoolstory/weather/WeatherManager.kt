package pro.piechowski.highschoolstory.weather

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class WeatherManager : KoinComponent {
    private val _weather = MutableStateFlow(Weather.CLEAR)

    val weather = _weather.asStateFlow()

    var currentWeather get() = weather.value
        set(value) {
            _weather.value = value
        }
}
