package logica.factorias;

import java.util.Scanner;
import logica.Ficha;
import logica.jugadores.Jugador;
import logica.jugadores.inteligentes.JugadorInteligenteConecta4;
import logica.jugadores.humanos.JugadorHumanoConecta4;
import logica.movimiento.Movimiento;
import logica.movimiento.MovimientoConecta4;
import logica.reglas.ReglasJuego;
import logica.reglas.ReglasJuegoConecta4;

public class FactoriaJuegoConecta4 implements FactoriaJuego {

    @Override
    public ReglasJuego crearReglas() {
        return new ReglasJuegoConecta4();
    }

    @Override
    public Movimiento crearMovimiento(int fila, int columna, Ficha color) {
        return new MovimientoConecta4(columna, color);
    }

    @Override
    public Jugador crearJugadorAleatorio() {
        return new JugadorInteligenteConecta4();
    }

    @Override
    public Jugador crearJugadorHumano(Scanner sc) {
        return new JugadorHumanoConecta4(sc);
    }

    @Override
    public String toString() {
        return "Conecta4";
    }

}
