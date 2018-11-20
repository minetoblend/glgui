#version 400
#extension GL_ARB_explicit_uniform_location : enable

in vec2 p;

layout(location = 100) uniform mat4 projectionMatrix;
layout(location = 101) uniform mat4 transformationMatrix;

void main(){
    gl_Position = projectionMatrix * transformationMatrix * vec4( p, 0.0, 1.0);
}