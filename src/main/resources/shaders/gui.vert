#version 400
#extension GL_ARB_explicit_uniform_location : enable

in vec2 p;

layout(location = 100) uniform mat4 projectionMatrix;
layout(location = 101) uniform mat4 viewMatrix;
layout(location = 102) uniform mat4 modelMatrix;



void main(){
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4( p, 0.0, 1.0);
}