#version 330 core

uniform sampler2D uTexture;

in vec2 vTexCoord;
in vec3 vNormal;

out vec4 fColor;

void main() {
	fColor = texture2D(uTexture, vTexCoord);
}