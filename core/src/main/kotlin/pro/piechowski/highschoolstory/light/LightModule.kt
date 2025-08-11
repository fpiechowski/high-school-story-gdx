package pro.piechowski.highschoolstory.light

import box2dLight.RayHandler
import org.koin.dsl.module
import pro.piechowski.highschoolstory.light.framebuffer.BeginLightFrameBufferSystem
import pro.piechowski.highschoolstory.light.framebuffer.EndLightFrameBufferSystem
import pro.piechowski.highschoolstory.light.framebuffer.LightFrameBufferManager

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

        single { LightFrameBufferManager() }
        single { BeginLightFrameBufferSystem(get()) }
        single { EndLightFrameBufferSystem(get()) }

        single { SunlightPostProcessingRenderingSystem() }
    }
