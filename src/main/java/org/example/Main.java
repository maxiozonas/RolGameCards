package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GeneradorPersonajes generador = new GeneradorPersonajes();

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
                    // Aqui debe ir el caso 1
                    break;
                case 2:
                    // Aqui debe ir el caso 2
                    break;
                case 3:
                    // Lógica para leer los logs de partidas jugadas
                    break;
                case 4:
                    // Lógica para borrar el archivo de logs
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