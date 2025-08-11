#ifdef GL_ES
precision mediump float;
#endif

varying vec2 v_texCoords;

uniform sampler2D u_scene;
uniform sampler2D u_light;
uniform vec3  u_ambientColor; // ← was float
uniform float u_gain;

vec3 toLinear(vec3 c){ return pow(c, vec3(2.2)); }
vec3 toGamma (vec3 c){ return pow(c, vec3(1.0/2.2)); }

void main() {
    vec3 scene = texture2D(u_scene, v_texCoords).rgb;
    vec3 L     = texture2D(u_light, v_texCoords).rgb;

    vec3 sceneLin = toLinear(scene);
    vec3 factor   = clamp(u_ambientColor + u_gain * L, 0.0, 1.0); // color ambient
    vec3 litLin   = sceneLin * factor;
    vec3 lit      = toGamma(litLin);

    gl_FragColor = vec4(lit, 1.0);
}
