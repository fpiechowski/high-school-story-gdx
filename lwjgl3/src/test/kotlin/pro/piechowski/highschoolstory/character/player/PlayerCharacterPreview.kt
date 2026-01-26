package pro.piechowski.highschoolstory.character.player

import org.junit.jupiter.api.Test
import pro.piechowski.highschoolstory.asset.Assets
import pro.piechowski.kge.asset.AssetsLoader
import pro.piechowski.highschoolstory.highSchoolStoryModule
import pro.piechowski.kge.di.DependencyInjection.Global.get
import pro.piechowski.kge.input.InputManager
import pro.piechowski.kge.preview.preview

class PlayerCharacterPreview {
    @Test
    fun preview() {
        preview(highSchoolStoryModule) {
            get<AssetsLoader<Assets>>().load()

            PlayerCharacter("Preview", "Character")
                .also { get<InputManager>().passOwnership(it) }
        }
    }
}
