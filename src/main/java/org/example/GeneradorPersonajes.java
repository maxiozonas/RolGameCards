package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneradorPersonajes {
    private static final List<String> NOMBRES = new ArrayList<>(List.of("Aragorn", "Legolas", "Gimli", "Gandalf", "Frodo", "Boromir", "Uhov", "Rand", "Eldor", "Thrain", "Drake", "Lorien"));
    private static final List<String> APODOS = new ArrayList<>(List.of("El Valiente", "El Arquero", "El Guerrero", "El Mago", "El Portador", "El Guardián", "El Destructor", "El Sabio", "El Fuerte", "El Astuto", "El Sombra", "El Protector"));
    private static final String[] FECHAS_NACIMIENTO = {"01-01-2000", "02-02-1990", "03-03-1985", "04-04-1970", "05-05-1960", "06-06-1950"};
    private static final String[] RAZAS = {"Humano", "Elfo", "Orco"};

    private List<Personaje> personajes;

    public GeneradorPersonajes() {
        this.personajes = new ArrayList<>();
    }

    // Método para generar personajes aleatorios
    public static List<Personaje> generarPersonajesAleatorios(int cantidad) {
        List<Personaje> personajesAleatorios = new ArrayList<>();
        Random random = new Random();
        List<String> nombresDisponibles = new ArrayList<>(NOMBRES);
        List<String> apodosDisponibles = new ArrayList<>(APODOS);

        Collections.shuffle(nombresDisponibles);
        Collections.shuffle(apodosDisponibles);

        for (int i = 0; i < cantidad; i++) {
            if (nombresDisponibles.isEmpty() || apodosDisponibles.isEmpty()) {
                throw new RuntimeException("No hay suficientes nombres o apodos únicos para generar los personajes.");
            }

            String raza = RAZAS[random.nextInt(RAZAS.length)];
            String nombre = nombresDisponibles.remove(0);
            String apodo = apodosDisponibles.remove(0);
            String fechaNacimiento = FECHAS_NACIMIENTO[random.nextInt(FECHAS_NACIMIENTO.length)];
            int edad = random.nextInt(300) + 1;
            int nivel = random.nextInt(10) + 1;
            int salud = 100;
            int velocidad = random.nextInt(10) + 1;
            int destreza = random.nextInt(5) + 1;
            int fuerza = random.nextInt(10) + 1;
            int armadura = random.nextInt(10) + 1;

            // Crear un nuevo personaje según la raza aleatoria
            Personaje nuevoPersonaje = null;

            if (raza.equalsIgnoreCase("Humano")) {
                nuevoPersonaje = new Humano(nombre, apodo, raza, fechaNacimiento, edad, nivel, salud, velocidad, destreza, fuerza, armadura);
            } else if (raza.equalsIgnoreCase("Elfo")) {
                nuevoPersonaje = new Elfo(nombre, apodo, raza, fechaNacimiento, edad, nivel, salud, velocidad, destreza, fuerza, armadura);
            } else if (raza.equalsIgnoreCase("Orco")) {
                nuevoPersonaje = new Orco(nombre, apodo, raza, fechaNacimiento, edad, nivel, salud, velocidad, destreza, fuerza, armadura);
            }

            if (nuevoPersonaje != null) {
                personajesAleatorios.add(nuevoPersonaje);
            }
        }

        return personajesAleatorios;
    }

    // Método para ingresar personajes manualmente
    public void ingresarPersonajesManualmente(String raza, String nombre, String apodo, String fechaNacimiento, int edad, int nivel, int velocidad, int destreza, int fuerza, int armadura) {
        // Verificar si el nombre y apodo ya están en uso
        if (personajes.stream().anyMatch(p -> p.getNombre().equals(nombre)) || personajes.stream().anyMatch(p -> p.getApodo().equals(apodo))) {
            System.out.println("Nombre o apodo ya en uso, elige otro.");
            return;
        }

        // Crear el personaje y agregarlo a la lista
        Personaje nuevoPersonaje = crearPersonaje(raza, nombre, apodo, fechaNacimiento, edad, nivel, 100, velocidad, destreza, fuerza, armadura);
        if (nuevoPersonaje != null) {
            this.personajes.add(nuevoPersonaje);
        }
    }


    // Método privado para crear un personaje según la raza
    private Personaje crearPersonaje(String raza, String nombre, String apodo, String fechaNacimiento, int edad, int nivel, int salud, int velocidad, int destreza, int fuerza, int armadura) {
        switch (raza.toLowerCase()) {
            case "humano":
                return new Humano(nombre, apodo, raza, fechaNacimiento, edad, nivel, salud, velocidad, destreza, fuerza, armadura);
            case "elfo":
                return new Elfo(nombre, apodo, raza, fechaNacimiento, edad, nivel, salud, velocidad, destreza, fuerza, armadura);
            case "orco":
                return new Orco(nombre, apodo, raza, fechaNacimiento, edad, nivel, salud, velocidad, destreza, fuerza, armadura);
            default:
                System.out.println("Raza desconocida: " + raza);
                return null;
        }
    }

    public List<Personaje> getPersonajes() {
        return this.personajes;
    }
}

