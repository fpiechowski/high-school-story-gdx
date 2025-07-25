package pro.piechowski.highschoolstory.physics

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import org.koin.dsl.module
import pro.piechowski.highschoolstory.gdx.PhysicsWorld
import pro.piechowski.highschoolstory.physics.body.PhysicsDebugRenderingSystem
import pro.piechowski.highschoolstory.physics.body.PhysicsWorldStepSystem

val PhysicsModule =
    module {
        single<Camera>(meterCameraQualifier) {
            OrthographicCamera(
                Gdx.graphics.width
                    .toFloat()
                    .px
                    .toMeter()
                    .value,
                Gdx.graphics.height
                    .toFloat()
                    .px
                    .toMeter()
                    .value,
            )
        }
        single<Viewport>(meterViewportQualifier) {
            FitViewport(
                1280f.px.toMeter().value,
                720f.px.toMeter().value,
                get(meterCameraQualifier),
            )
        }
        single { PhysicsWorld(Vector2.Zero.cpy(), true) }
        single { PhysicsWorldStepSystem() }
        single { PhysicsDebugRenderingSystem() }
        single { Box2DDebugRenderer() }
    }
