package logica.factorias;

import java.util.Scanner;
import logica.Ficha;
import logica.jugadores.Jugador;
import logica.jugadores.aleatorios.JugadorAleatorioReversi;
import logica.jugadores.humanos.JugadorHumanoReversi;
import logica.movimiento.Movimiento;
import logica.movimiento.MovimientoReversi;
import logica.reglas.ReglasJuego;
import logica.reglas.ReglasJuegoReversi;

public class FactoriaJuegoReversi implements FactoriaJuego {

    @Override
    public ReglasJuego crearReglas() {
        return new ReglasJuegoReversi();
    }

    @Override
    public Movimiento crearMovimiento(int fila, int columna, Ficha color) {
        return new MovimientoReversi(fila, columna, color);
    }

    @Override
    public Jugador crearJugadorAleatorio() {
        return new JugadorAleatorioReversi();
    }

    @Override
    public Jugador crearJugadorHumano(Scanner sc) {
        return new JugadorHumanoReversi(sc);
    }

    @Override
    public String toString() {
        return "Reversi";
    }
}