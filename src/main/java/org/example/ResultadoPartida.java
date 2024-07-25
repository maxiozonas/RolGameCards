package org.example;

import java.util.List;

public class ResultadoPartida {
    private List<Personaje> jugadoresOriginales1;
    private List<Personaje> jugadoresOriginales2;
    private List<Personaje> jugadoresSobrevivientes1;
    private List<Personaje> jugadoresSobrevivientes2;
    private String ganador;

    public ResultadoPartida(List<Personaje> jugadoresOriginales1, List<Personaje> jugadoresOriginales2, List<Personaje> jugadoresSobrevivientes1, List<Personaje> jugadoresSobrevivientes2, String ganador) {
        this.jugadoresOriginales1 = jugadoresOriginales1;
        this.jugadoresOriginales2 = jugadoresOriginales2;
        this.jugadoresSobrevivientes1 = jugadoresSobrevivientes1;
        this.jugadoresSobrevivientes2 = jugadoresSobrevivientes2;
        this.ganador = ganador;
    }

    public List<Personaje> getJugadoresOriginales1() {
        return jugadoresOriginales1;
    }

    public void setJugadoresOriginales1(List<Personaje> jugadoresOriginales1) {
        this.jugadoresOriginales1 = jugadoresOriginales1;
    }

    public List<Personaje> getJugadoresOriginales2() {
        return jugadoresOriginales2;
    }

    public void setJugadoresOriginales2(List<Personaje> jugadoresOriginales2) {
        this.jugadoresOriginales2 = jugadoresOriginales2;
    }

    public List<Personaje> getJugadoresSobrevivientes1() {
        return jugadoresSobrevivientes1;
    }

    public void setJugadoresSobrevivientes1(List<Personaje> jugadoresSobrevivientes1) {
        this.jugadoresSobrevivientes1 = jugadoresSobrevivientes1;
    }

    public List<Personaje> getJugadoresSobrevivientes2() {
        return jugadoresSobrevivientes2;
    }

    public void setJugadoresSobrevivientes2(List<Personaje> jugadoresSobrevivientes2) {
        this.jugadoresSobrevivientes2 = jugadoresSobrevivientes2;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
}
