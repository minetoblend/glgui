#version 400

in layout(location = 100) vec2 p;
in layout(location = 101) vec2 uv;
in layout(location = 102) vec3 n;
in layout(location = 103) vec4 col;

uniform layout(location = 200) mat4 projectionMatrix;
uniform layout(location = 201) mat4 transformationMatrix;


out vec3 normal;
out vec4 color;

void main(){
    gl_Position = projectionMatrix * transformationMatrix * vec4(p, 0.0, 1.0);
    normal = vec3(transformationMatrix * vec4(n, 1.0));
    color = col;
}