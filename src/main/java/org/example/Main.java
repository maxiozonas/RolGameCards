package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GeneradorPersonajes generador = new GeneradorPersonajes();
        Gameplay gameplay = new Gameplay();

        boolean salir = false;

        while (!salir) {
            System.out.println("Bienvenido al juego de combate de cartas RPG");
            System.out.println("1. Iniciar partida y generar 6 personajes aleatoriamente");
            System.out.println("2. Iniciar partida y ingresar personajes manualmente");
            System.out.println("3. Leer logs de partidas jugadas");
            System.out.println("4. Borrar archivo de logs");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después del nextInt()

            switch (opcion) {
                case 1:
                    List<Personaje> personajesAleatorios = GeneradorPersonajes.generarPersonajesAleatorios(6);
                    gameplay.iniciarPartida(personajesAleatorios);
                    break;
                case 2:
                    for (int i = 0; i < 6; i++) {
                        System.out.println("Ingrese los detalles del personaje " + (i + 1));
                        System.out.print("Raza (Humano/Elfo/Orco): ");
                        String raza = scanner.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Apodo: ");
                        String apodo = scanner.nextLine();
                        System.out.print("Fecha de Nacimiento (dd-mm-yyyy): ");
                        String fechaNacimiento = scanner.nextLine();
                        System.out.print("Edad (1-300): ");
                        int edad = scanner.nextInt();
                        System.out.print("Nivel (1-10): ");
                        int nivel = scanner.nextInt();
                        System.out.print("Velocidad (1-10): ");
                        int velocidad = scanner.nextInt();
                        System.out.print("Destreza (1-5): ");
                        int destreza = scanner.nextInt();
                        System.out.print("Fuerza (1-10): ");
                        int fuerza = scanner.nextInt();
                        System.out.print("Armadura (1-10): ");
                        int armadura = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea después del nextInt()

                        generador.ingresarPersonajesManualmente(raza, nombre, apodo, fechaNacimiento, edad, nivel, velocidad, destreza, fuerza, armadura);
                    }
                    List<Personaje> personajesManuales = generador.getPersonajes();
                    gameplay.iniciarPartida(personajesManuales);
                    break;
                case 3:
                    gameplay.leerLogs();
                    break;
                case 4:
                    gameplay.borrarLogs();
                    break;
                case 5:
                    salir = true;
                    System.out.println("¡Gracias por jugar!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor seleccione una opción del 1 al 5.");
                    break;
            }
        }
    }
}
