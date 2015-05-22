package logica.factorias;

import java.util.Scanner;
import logica.Ficha;
import logica.jugadores.Jugador;
import logica.jugadores.inteligentes.JugadorInteligenteGravity;
import logica.jugadores.humanos.JugadorHumanoGravity;
import logica.movimiento.Movimiento;
import logica.movimiento.MovimientoGravity;
import logica.reglas.ReglasJuego;
import logica.reglas.ReglasJuegoGravity;

public class FactoriaJuegoGravity implements FactoriaJuego {

    @Override
    public ReglasJuego crearReglas() {
        return new ReglasJuegoGravity();
    }

    @Override
    public Movimiento crearMovimiento(int fila, int columna, Ficha color) {
        return new MovimientoGravity(fila, columna, color);
    }

    @Override
    public Jugador crearJugadorAleatorio() {
        return new JugadorInteligenteGravity();
    }

    @Override
    public Jugador crearJugadorHumano(Scanner sc) {
        return new JugadorHumanoGravity(sc);
    }

    @Override
    public String toString() {
        return "Gravity";
    }

}
