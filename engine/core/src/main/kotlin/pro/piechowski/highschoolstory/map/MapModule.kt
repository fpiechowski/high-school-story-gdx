package pro.piechowski.highschoolstory.map

import org.koin.dsl.module
import pro.piechowski.highschoolstory.rendering.map.MapRenderingSystem

val MapModule =
    module {
        single { MapManager() }
        single { MapRenderingSystem.Background() }
        single { MapRenderingSystem.Foreground() }
    }
