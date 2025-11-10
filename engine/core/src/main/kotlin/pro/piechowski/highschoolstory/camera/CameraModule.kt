package pro.piechowski.highschoolstory.camera

import org.koin.dsl.module
import pro.piechowski.highschoolstory.Config
import pro.piechowski.highschoolstory.ui.UserInterfaceViewport

val CoreCameraModule =
    module {
        single { CameraManager() }

        single<UserInterfaceViewport> { UserInterfaceViewport() }
    }

val GameCameraModule =
    module {
        single<MeterCamera> { MeterCamera(get<Config>().meterCamera) }
        single<MeterViewport> { MeterViewport(get()) }
    }
