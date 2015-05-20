package logica.factorias;

import java.util.Scanner;
import logica.Ficha;
import logica.jugadores.Jugador;
import logica.movimiento.Movimiento;
import logica.reglas.ReglasJuego;

public interface FactoriaJuego {

    public ReglasJuego crearReglas();

    public Movimiento crearMovimiento(int fila, int columna, Ficha color);

    public Jugador crearJugadorAleatorio();

    public Jugador crearJugadorHumano(Scanner sc);
   
}
