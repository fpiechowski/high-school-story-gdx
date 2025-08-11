package pro.piechowski.highschoolstory.weather

import org.koin.dsl.module

val WeatherModule =
    module {
        single { WeatherManager() }
    }
