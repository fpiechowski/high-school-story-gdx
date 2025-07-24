package pro.piechowski.highschoolstory

import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.async.KtxAsync
import org.koin.core.Koin
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class Main : KtxGame<KtxScreen>() {
    override fun create() {
        KtxAsync.initiate()

        val koin =
            startKoin {
                modules(mainModule())
            }.koin

        with(koin) {
            startGame()
        }
    }

    context(koin: Koin)
    fun startGame() {
        loadKoinModules(koin.get<Module>(gameModuleQualifier))
        addScreen(koin.get<GameScreen>())
        setScreen<GameScreen>()
    }
}
