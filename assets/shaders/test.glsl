#type vertex
#version 330 core

layout(location = 0) in vec3 aPos;
layout(location = 1) in vec4 aColor;
layout(location = 2) in vec2 aTexCoords;

uniform mat4 uProjection;
uniform mat4 uView;

out vec2 fTexCoords;
out vec4 fColor;

void main() {
    fColor = aColor;
    fTexCoords = aTexCoords;
    gl_Position = uProjection * uView * vec4(aPos, 1.0);
    //    gl_Position = vec4(aPos, 1);

}


#type fragment
#version 330 core

in vec4 fColor;
in vec2 fTexCoords;

uniform sampler2D TEX_SAMPLER;

out vec4 color;

void main() {
    //    color = fColor;
    color = texture(TEX_SAMPLER, fTexCoords);
}
