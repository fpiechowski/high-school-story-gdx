package pro.piechowski.highschoolstory.shader

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import org.koin.core.component.KoinComponent

class ShaderManager : KoinComponent {
    val lightingCompositeShader: ShaderProgram

    init {
        ShaderProgram.pedantic = false

        lightingCompositeShader =
            ShaderProgram(
                Gdx.files.internal("shaders/composite.vert"),
                Gdx.files.internal("shaders/composite.frag"),
            )
        require(lightingCompositeShader.isCompiled) { lightingCompositeShader.log }
        require(lightingCompositeShader.hasUniform("u_scene")) { "u_scene missing" }
        require(lightingCompositeShader.hasUniform("u_light")) { "u_light missing" }
        require(lightingCompositeShader.hasUniform("u_ambientColor")) { "u_ambientColor missing" } // <- catches the mismatch
        require(lightingCompositeShader.hasUniform("u_gain")) { "u_gain missing" }
    }
}
