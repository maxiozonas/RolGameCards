package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GeneradorPersonajes {
    private static final String[] NOMBRES = {"Aragorn", "Legolas", "Gimli", "Gandalf", "Frodo", "Boromir"};
    private static final String[] APODOS = {"El Valiente", "El Arquero", "El Guerrero", "El Mago", "El Portador", "El Guardián"};
    private static final String[] FECHAS_NACIMIENTO = {"01-01-2000", "02-02-1990", "03-03-1985", "04-04-1970", "05-05-1960", "06-06-1950"};
    private static final String[] RAZAS = {"Humano", "Elfo", "Orco"};

    private List<Personaje> personajes;

    public GeneradorPersonajes() {
        this.personajes = new ArrayList<>();
    }

    // METODO PARA GENERAR PERSONAJES ALEATORIOS
    public static List<Personaje> generarPersonajesAleatorios (int cantidad) {
        List<Personaje> personajesAleatorios = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
            String raza =RAZAS[random.nextInt(RAZAS.length)];
            String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
            String apodo = APODOS[random.nextInt(APODOS.length)];
            String fechaNacimiento = FECHAS_NACIMIENTO[random.nextInt(FECHAS_NACIMIENTO.length)];
            int edad = random.nextInt(300) + 1;
            int salud = 100;
            int velocidad = random.nextInt(10) + 1;
            int destreza = random.nextInt(5) + 1;
            int fuerza = random.nextInt(10) + 1;
            int armadura = random.nextInt(10) + 1;

            // Crear un nuevo personaje según la raza aleatoria
            Personaje nuevoPersonaje = null;

            if (raza.equalsIgnoreCase("Humano")) {
                nuevoPersonaje = new Humano(nombre, apodo, raza, fechaNacimiento, edad, salud,  velocidad, destreza, fuerza, armadura);
            } else if (raza.equalsIgnoreCase("Elfo")) {
                nuevoPersonaje = new Elfo(nombre, apodo, raza, fechaNacimiento, edad, salud,  velocidad, destreza, fuerza, armadura);
            } else if (raza.equalsIgnoreCase("Orco")) {
                nuevoPersonaje = new Orco(nombre, apodo, raza, fechaNacimiento, edad, salud,  velocidad, destreza, fuerza, armadura);
            }

            if (nuevoPersonaje != null) {
                personajesAleatorios.add(nuevoPersonaje);
            }
        }

        return  personajesAleatorios;
    }


    // METODO PARA INGRESAR PERSONAJES MANUALMENTE
    public void ingresarPersonajesManualmente(String raza, String nombre, String apodo, String fechaNacimiento, int edad, int velocidad, int destreza, int fuerza, int armadura) {
        // Crear un nuevo personaje según la raza
        Personaje nuevoPersonaje = null;
        int salud = 100;

        if (raza.equalsIgnoreCase("Humano")) {
            nuevoPersonaje = new Humano(nombre, apodo, raza, fechaNacimiento, edad, salud,  velocidad, destreza, fuerza, armadura);
        } else if (raza.equalsIgnoreCase("Elfo")) {
            nuevoPersonaje = new Elfo(nombre, apodo, raza, fechaNacimiento, edad, salud,  velocidad, destreza, fuerza, armadura);
        } else if (raza.equalsIgnoreCase("Orco")) {
            nuevoPersonaje = new Orco(nombre, apodo, raza, fechaNacimiento, edad, salud,  velocidad, destreza, fuerza, armadura);
        }

        // Agregar el nuevo personaje a la lista de personajes
        if (nuevoPersonaje != null) {
            this.personajes.add(nuevoPersonaje);
        }
    }

    public List<Personaje> getPersonajes() {
        return this.personajes;
    }
}
