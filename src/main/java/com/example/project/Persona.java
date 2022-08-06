package com.example.project;

public class Persona {
    private String DNI;
    private String nombre;

    public Persona(String DNI, String nombre){
        this.DNI = DNI;
        this.nombre = nombre;
    }

    public String getDNI() {
        return this.DNI;
    }

    public String getNombre() {
        return this.nombre;
    }
}
