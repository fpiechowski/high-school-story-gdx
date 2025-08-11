package pro.piechowski.highschoolstory.light

import box2dLight.RayHandler
import com.badlogic.gdx.graphics.Color
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import ktx.async.KtxAsync
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.time.calendar.Calendar
import pro.piechowski.highschoolstory.time.clock.Clock
import pro.piechowski.highschoolstory.weather.Weather
import pro.piechowski.highschoolstory.weather.WeatherManager
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.pow

class SunLightManager : KoinComponent
