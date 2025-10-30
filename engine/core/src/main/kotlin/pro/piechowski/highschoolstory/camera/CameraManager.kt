package pro.piechowski.highschoolstory.camera

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class CameraManager : KoinComponent {
    private val _followingPlayerCharacter = MutableStateFlow(false)

    val followingPlayerCharacter = _followingPlayerCharacter.asStateFlow()
    var followingPlayerCharacterValue get() = _followingPlayerCharacter.value
        set(value) {
            _followingPlayerCharacter.value = value
        }

    fun followPlayerCharacter() {
        followingPlayerCharacterValue = true
    }

    fun stopFollowingPlayerCharacter() {
        followingPlayerCharacterValue = false
    }
}
