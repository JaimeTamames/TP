package logica.jugadores;

import logica.Ficha;
import logica.tablero.Tablero;
import logica.factorias.FactoriaJuego;
import logica.movimiento.Movimiento;

public abstract class Jugador {

    protected int fila;
    protected int columna;

    protected abstract void obtenFilaColumna(Tablero tab, Ficha color);

    public Movimiento getMovimiento(FactoriaJuego factoria, Tablero tab, Ficha color) {

        this.obtenFilaColumna(tab, color);
        return factoria.crearMovimiento(this.fila, this.columna, color);

    }
}
