#version 400
#extension GL_ARB_explicit_uniform_location : enable

layout(location = 200) uniform vec4 color;

out vec4 out_Color;

void main(void){
    out_Color = color;
}