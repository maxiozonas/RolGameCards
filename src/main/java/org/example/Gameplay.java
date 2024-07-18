package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gameplay {
    private static final int ATAQUES_POR_RONDA = 7;
    private static final int TIEMPO_ESPERA = 1000; // 1 segundo
    private List<Personaje> jugadoresOriginales1;
    private List<Personaje> jugadoresOriginales2;

    public void iniciarPartida(List<Personaje> personajes) {
        if (personajes.size() != 6) {
            throw new IllegalArgumentException("Se requieren exactamente 6 personajes para iniciar la partida");
        }

        // Mostrar los personajes creados
        System.out.println("Personajes creados:");
        for (int i = 0; i < personajes.size(); i++) {
            System.out.println("Personaje " + (i + 1) + ": " + personajes.get(i));
        }

        List<Personaje> jugadores1 = new ArrayList<>(personajes.subList(0, 3));
        List<Personaje> jugadores2 = new ArrayList<>(personajes.subList(3, 6));

        this.jugadoresOriginales1 = new ArrayList<>(jugadores1);
        this.jugadoresOriginales2 = new ArrayList<>(jugadores2);

        // Mostrar personajes de cada jugador
        System.out.println("\nSe generaron 6 personajes:");
        mostrarPersonajes(jugadores1, "Jugador 1");
        mostrarPersonajes(jugadores2, "Jugador 2");

        int ronda = 1;
        boolean juegoActivo = true;

        Random random = new Random();
        boolean turnoJugador1 = random.nextBoolean(); // Sortea quién inicia la primera ronda

        while (juegoActivo) {
            System.out.println("\nRonda " + ronda);
            if (turnoJugador1) {
                System.out.println("El Jugador 1 comienza atacando.");
            } else {
                System.out.println("El Jugador 2 comienza atacando.");
            }

            // Seleccionar personajes aleatoriamente para la ronda
            Personaje personaje1 = jugadores1.get(random.nextInt(jugadores1.size()));
            Personaje personaje2 = jugadores2.get(random.nextInt(jugadores2.size()));
            System.out.println("El sistema eligió al personaje " + personaje1.getApodo() + " del Jugador 1 y al personaje " + personaje2.getApodo() + " del Jugador 2 para que se enfrenten en esta ronda.");

            int ataquesPorRonda = ATAQUES_POR_RONDA;
            while (ataquesPorRonda > 0 && personaje1.getSalud() > 0 && personaje2.getSalud() > 0) {
                if (turnoJugador1) {
                    atacar(personaje1, personaje2);
                    turnoJugador1 = false;
                } else {
                    atacar(personaje2, personaje1);
                    turnoJugador1 = true;
                }
                ataquesPorRonda--;

                // Pausar antes de mostrar el siguiente log
                try {
                    Thread.sleep(TIEMPO_ESPERA);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("La espera fue interrumpida");
                }
            }

            manejarFinDeRonda(personaje1, personaje2, jugadores1, jugadores2);

            // Comprobar si algún jugador se ha quedado sin personajes
            if (jugadores1.isEmpty() || jugadores2.isEmpty()) {
                juegoActivo = false;
            }

            ronda++;
        }

        // Declarar al ganador y mostrar los honores
        declararGanador(jugadores1, jugadores2);

        // Guardar el log de la partida
        guardarLog(jugadoresOriginales1, jugadoresOriginales2, jugadores1, jugadores2,
                jugadores1.isEmpty() ? "Jugador 2" : "Jugador 1");
    }

    private void mostrarPersonajes(List<Personaje> personajes, String jugador) {
        System.out.println("\nPersonajes del " + jugador + ":");
        for (int i = 0; i < personajes.size(); i++) {
            System.out.println("------ Personaje " + (i + 1) + " " + jugador + " ------");
            System.out.println(personajes.get(i));
        }
    }

    private void manejarFinDeRonda(Personaje personaje1, Personaje personaje2, List<Personaje> jugadores1, List<Personaje> jugadores2) {
        // Mostrar salud restante de ambos personajes
        System.out.println("Después de la ronda, la salud de los personajes es:");
        System.out.println(personaje1.getApodo() + ": " + personaje1.getSalud() + " de salud.");
        System.out.println(personaje2.getApodo() + ": " + personaje2.getSalud() + " de salud.");

        // Revisar si algún personaje ha sido eliminado
        if (personaje1.getSalud() <= 0) {
            System.out.println(personaje1.getApodo() + " ha muerto. Jugador 2 gana la ronda.");
            jugadores1.remove(personaje1);
            personaje2.mejorarSalud();
            personaje2.mejorarNivel();
        } else if (personaje2.getSalud() <= 0) {
            System.out.println(personaje2.getApodo() + " ha muerto. Jugador 1 gana la ronda.");
            jugadores2.remove(personaje2);
            personaje1.mejorarSalud();
            personaje1.mejorarNivel();
        } else {
            System.out.println("La ronda terminó sin muertes. Ambos personajes sobreviven.");
        }
    }

    private void atacar(Personaje atacante, Personaje defensor) {
        Random random = new Random();

        double pd = atacante.getDestreza() * atacante.getFuerza() * atacante.getNivel();
        int edRandom = random.nextInt(100) + 1;
        double ed = edRandom / 100.0;
        double va = pd * ed;
        double pdef = defensor.getArmadura() * defensor.getVelocidad();

        double danio = atacante.calcularDanio(va, ed, pdef);
        if (danio < 0) danio = 0; // Asegurarse de que el daño no sea negativo
        defensor.setSalud(defensor.getSalud() - (int) danio);
        if (defensor.getSalud() < 0) {
            defensor.setSalud(0); // Asegurar que la salud no sea negativa
        }

        System.out.println(atacante.getApodo() + " ataca a " + defensor.getApodo() + " y le quita " + (int) danio + " de salud. " + defensor.getApodo() + " queda con " + defensor.getSalud());
    }

    private void declararGanador(List<Personaje> jugadores1, List<Personaje> jugadores2) {
        List<Personaje> ganadores;
        List<Personaje> perdedores;
        String jugadorGanador;
        String jugadorPerdedor;

        if (jugadores1.isEmpty()) {
            ganadores = jugadores2;
            perdedores = jugadores1;
            jugadorGanador = "Jugador 2";
            jugadorPerdedor = "Jugador 1";
        } else {
            ganadores = jugadores1;
            perdedores = jugadores2;
            jugadorGanador = "Jugador 1";
            jugadorPerdedor = "Jugador 2";
        }

        System.out.println("\n¡VICTORIA! ¡LARGA VIDA AL NUEVO OCUPANTE DEL TRONO DE HIERRO!");
        System.out.println("********************************************************");
        System.out.println("   El " + jugadorGanador + " ha ganado la partida y es merecedor del Trono de Hierro");
        System.out.println("********************************************************");

        System.out.println("\nPersonajes victoriosos del " + jugadorGanador + ":");
        for (Personaje p : ganadores) {
            System.out.println("- " + p.getNombre() + " (" + p.getRaza() + ") - Nivel: " + p.getNivel() + " - Salud: " + p.getSalud());
        }

        System.out.println("\nPersonajes caídos en batalla del " + jugadorPerdedor + ":");
        List<Personaje> personajesCaidos = new ArrayList<>(jugadorGanador.equals("Jugador 1") ? jugadoresOriginales2 : jugadoresOriginales1);
        personajesCaidos.removeAll(perdedores);

        if (!personajesCaidos.isEmpty()) {
            for (Personaje p : personajesCaidos) {
                System.out.println("- " + p.getNombre() + " (" + p.getRaza() + ") - Nivel: " + p.getNivel() + " - Salud: 0");
            }
        } else {
            System.out.println("No hay personajes caídos registrados.");
        }

        System.out.println("\n¡Que el reinado del " + jugadorGanador + " sea largo y próspero!");
    }

    private void guardarLog(List<Personaje> jugadoresOriginales1, List<Personaje> jugadoresOriginales2,
                            List<Personaje> jugadoresSobrevivientes1, List<Personaje> jugadoresSobrevivientes2,
                            String ganador) {

        String fileName = "partidas_log.txt";
        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = now.format(formatter);

            out.println("Fecha y hora de la partida: " + formattedDate);

            out.println("Personajes del Jugador 1:");
            for (Personaje p : jugadoresOriginales1) {
                boolean sobrevivio = jugadoresSobrevivientes1.contains(p);
                out.println("- " + p.getNombre() + " (" + p.getRaza() + ") - " +
                        (sobrevivio ? "Sobrevivió" : "Caído en combate"));
            }

            out.println("Personajes del Jugador 2:");
            for (Personaje p : jugadoresOriginales2) {
                boolean sobrevivio = jugadoresSobrevivientes2.contains(p);
                out.println("- " + p.getNombre() + " (" + p.getRaza() + ") - " +
                        (sobrevivio ? "Sobrevivió" : "Caído en combate"));
            }

            out.println("Ganador de la partida: " + ganador);
            out.println("----------------------------------------");
        } catch (IOException e) {
            System.out.println("Error al guardar el log: " + e.getMessage());
        }
    }

    public void leerLogs() {
        String fileName = "partidas_log.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el log: " + e.getMessage());
        }
    }

    public void borrarLogs() {
        String fileName = "partidas_log.txt";
        File file = new File(fileName);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("El archivo de logs ha sido borrado exitosamente.");
            } else {
                System.out.println("No se pudo borrar el archivo de logs. Verifica los permisos del archivo.");
            }
        } else {
            System.out.println("El archivo de logs no existe.");
        }
    }
}