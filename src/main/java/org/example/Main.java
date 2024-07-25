package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que maneja la interacción con el usuario y coordina el juego.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GeneradorPersonajes generador = new GeneradorPersonajes();
        Gameplay gameplay = new Gameplay();
        LogManager logManager = new LogManager();

        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = obtenerOpcionUsuario(scanner);

            switch (opcion) {
                case 1:
                    jugarPartidaAleatoria(gameplay, logManager);
                    break;
                case 2:
                    jugarPartidaManual(generador, gameplay, logManager, scanner);
                    break;
                case 3:
                    logManager.leerLogs();
                    break;
                case 4:
                    logManager.borrarLogs();
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

        scanner.close();
    }

    /**
     * Muestra el menú principal del juego.
     */
    private static void mostrarMenu() {
        System.out.println("\nBienvenido al juego de combate de cartas RPG");
        System.out.println("1. Iniciar partida y generar 6 personajes aleatoriamente");
        System.out.println("2. Iniciar partida y ingresar personajes manualmente");
        System.out.println("3. Leer logs de partidas jugadas");
        System.out.println("4. Borrar archivo de logs");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Obtiene la opción seleccionada por el usuario.
     * @param scanner Scanner para leer la entrada del usuario.
     * @return La opción seleccionada por el usuario.
     */
    private static int obtenerOpcionUsuario(Scanner scanner) {
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después del nextInt()
        return opcion;
    }

    /**
     * Inicia una partida con personajes generados aleatoriamente.
     * @param gameplay Instancia de Gameplay para manejar la lógica del juego.
     * @param logManager Instancia de LogManager para manejar los logs.
     */
    private static void jugarPartidaAleatoria(Gameplay gameplay, LogManager logManager) {
        List<Personaje> personajesAleatorios = GeneradorPersonajes.generarPersonajesAleatorios(6);
        ResultadoPartida resultado = gameplay.iniciarPartida(personajesAleatorios);
        logManager.guardarLog(resultado);
    }

    /**
     * Inicia una partida con personajes ingresados manualmente por el usuario.
     * @param generador Instancia de GeneradorPersonajes para crear personajes.
     * @param gameplay Instancia de Gameplay para manejar la lógica del juego.
     * @param logManager Instancia de LogManager para manejar los logs.
     * @param scanner Scanner para leer la entrada del usuario.
     */
    private static void jugarPartidaManual(GeneradorPersonajes generador, Gameplay gameplay, LogManager logManager, Scanner scanner) {
        for (int i = 0; i < 6; i++) {
            System.out.println("Ingrese los detalles del personaje " + (i + 1));
            ingresarPersonajeManualmente(generador, scanner);
        }
        List<Personaje> personajesManuales = generador.getPersonajes();
        ResultadoPartida resultado = gameplay.iniciarPartida(personajesManuales);
        logManager.guardarLog(resultado);
    }

    /**
     * Solicita al usuario los detalles de un personaje y lo agrega al generador.
     * @param generador Instancia de GeneradorPersonajes para agregar el nuevo personaje.
     * @param scanner Scanner para leer la entrada del usuario.
     */
    private static void ingresarPersonajeManualmente(GeneradorPersonajes generador, Scanner scanner) {
        System.out.print("Raza (Humano/Elfo/Orco): ");
        String raza = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apodo: ");
        String apodo = scanner.nextLine();
        System.out.print("Fecha de Nacimiento (dd-mm-yyyy): ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Edad (1-300): ");
        int edad = obtenerEnteroValido(scanner, 1, 300);
        System.out.print("Nivel (1-10): ");
        int nivel = obtenerEnteroValido(scanner, 1, 10);
        System.out.print("Velocidad (1-10): ");
        int velocidad = obtenerEnteroValido(scanner, 1, 10);
        System.out.print("Destreza (1-5): ");
        int destreza = obtenerEnteroValido(scanner, 1, 5);
        System.out.print("Fuerza (1-10): ");
        int fuerza = obtenerEnteroValido(scanner, 1, 10);
        System.out.print("Armadura (1-10): ");
        int armadura = obtenerEnteroValido(scanner, 1, 10);

        generador.ingresarPersonajesManualmente(raza, nombre, apodo, fechaNacimiento, edad, nivel, velocidad, destreza, fuerza, armadura);
    }

    /**
     * Obtiene un entero válido dentro de un rango específico.
     * @param scanner Scanner para leer la entrada del usuario.
     * @param min Valor mínimo aceptable.
     * @param max Valor máximo aceptable.
     * @return Un entero válido dentro del rango especificado.
     */
    private static int obtenerEnteroValido(Scanner scanner, int min, int max) {
        int valor;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // Descartar la entrada no válida
            }
            valor = scanner.nextInt();
            if (valor < min || valor > max) {
                System.out.println("Por favor, ingrese un número entre " + min + " y " + max + ".");
            }
        } while (valor < min || valor > max);
        scanner.nextLine(); // Consumir la nueva línea
        return valor;
    }
}