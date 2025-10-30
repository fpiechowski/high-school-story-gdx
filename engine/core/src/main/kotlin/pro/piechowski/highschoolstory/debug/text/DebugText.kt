package pro.piechowski.highschoolstory.debug.text

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.EntityComponentContext

data class DebugText(
    val getText: context(EntityComponentContext)
    Entity.() -> String,
) : Component<DebugText> {
    override fun type(): ComponentType<DebugText> = DebugText

    companion object : ComponentType<DebugText>()
}
