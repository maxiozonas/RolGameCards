package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Clase que maneja la gestión de logs del juego.
 */
public class LogManager {
    private static final String LOG_FILE_NAME = "partidas_log.txt";

    /**
     * Guarda el log de una partida en el archivo de logs.
     * @param resultado ResultadoPartida con la información de la partida a guardar.
     */
    public void guardarLog(ResultadoPartida resultado) {
        try (FileWriter fw = new FileWriter(LOG_FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = now.format(formatter);

            out.println("Fecha y hora de la partida: " + formattedDate);

            escribirPersonajes(out, "Jugador 1", resultado.getJugadoresOriginales1(), resultado.getJugadoresSobrevivientes1());
            escribirPersonajes(out, "Jugador 2", resultado.getJugadoresOriginales2(), resultado.getJugadoresSobrevivientes2());

            out.println("Ganador de la partida: " + resultado.getGanador());
            out.println("----------------------------------------");
        } catch (IOException e) {
            System.out.println("Error al guardar el log: " + e.getMessage());
        }
    }

    /**
     * Escribe la información de los personajes de un jugador en el log.
     */
    private void escribirPersonajes(PrintWriter out, String jugador, List<Personaje> originales, List<Personaje> sobrevivientes) {
        out.println("Personajes del " + jugador + ":");
        for (Personaje p : originales) {
            boolean sobrevivio = sobrevivientes.contains(p);
            out.println("- " + p.getNombre() + " (" + p.getRaza() + ") - " +
                    (sobrevivio ? "Sobrevivió" : "Caído en combate"));
        }
    }

    /**
     * Lee y muestra los logs de partidas anteriores.
     */
    public void leerLogs() {
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el log: " + e.getMessage());
        }
    }

    /**
     * Borra el archivo de logs.
     */
    public void borrarLogs() {
        File file = new File(LOG_FILE_NAME);
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
