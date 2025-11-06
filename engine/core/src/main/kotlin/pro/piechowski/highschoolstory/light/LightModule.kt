package pro.piechowski.highschoolstory.light

import box2dLight.RayHandler
import org.koin.dsl.module
import pro.piechowski.highschoolstory.rendering.light.LightRenderingSystem

val LightModule =
    module {
        single {
            RayHandler(get()).apply {
                RayHandler.setGammaCorrection(true)
                RayHandler.useDiffuseLight(true)
                setShadows(true)
                setBlur(true)
            }
        }
        single { SunLightManager() }

        single { LightRenderingSystem() }
    }
