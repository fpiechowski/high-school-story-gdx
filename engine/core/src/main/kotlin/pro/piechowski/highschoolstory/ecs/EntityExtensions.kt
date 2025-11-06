package pro.piechowski.highschoolstory.ecs

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.EntityComponentContext

context(ecc: EntityComponentContext)
inline operator fun <reified T : Component<*>> Entity.get(componentType: ComponentType<T>): T =
    with(ecc) {
        this@get[componentType]
    }
