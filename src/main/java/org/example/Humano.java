package org.example;

public class Humano extends Personaje {
    public Humano(String nombre, String apodo, String raza, String fechaNacimiento, int edad, int nivel, int salud, int velocidad, int destreza, int fuerza, int armadura) {
        super(nombre, apodo, raza, fechaNacimiento, edad, nivel, salud, velocidad, destreza, fuerza, armadura);
    }

    @Override
    public double calcularDanio(double va, double ed, double pdef) {
        return (((va * ed) - pdef) / 500) * 100;
    }
}

