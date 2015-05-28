package logica.factorias;

import java.util.Scanner;
import logica.Ficha;
import logica.jugadores.Jugador;
import logica.jugadores.inteligentes.JugadorInteligenteComplica;
import logica.jugadores.humanos.JugadorHumanoComplica;
import logica.movimiento.Movimiento;
import logica.movimiento.MovimientoComplica;
import logica.reglas.ReglasJuego;
import logica.reglas.ReglasJuegoComplica;

public class FactoriaJuegoComplica implements FactoriaJuego {

    @Override
    public ReglasJuego crearReglas() {
        return new ReglasJuegoComplica();
    }

    @Override
    public Movimiento crearMovimiento(int fila, int columna, Ficha color) {
        return new MovimientoComplica(columna, color);
    }

    @Override
    public Jugador crearJugadorInteligente() {
        return new JugadorInteligenteComplica();
    }

    @Override
    public Jugador crearJugadorHumano(Scanner sc) {
        return new JugadorHumanoComplica(sc);
    }

    @Override
    public String toString() {
        return "Complica";
    }

}
