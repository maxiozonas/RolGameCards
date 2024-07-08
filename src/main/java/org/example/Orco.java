package org.example;

public class Orco extends Personaje{
    public Orco(String nombre, String apodo, String raza, String fechaNacimiento, int edad, int salud, int velocidad, int destreza, int fuerza, int armadura) {
        super(nombre, apodo, raza, fechaNacimiento, edad, salud, velocidad, destreza, fuerza, armadura);
    }

    @Override
    public double calcularDanio(double va, double ed, double pdef) {
        return (((((va * ed) - pdef) / 500) * 100) * 1.1);
    }
}
