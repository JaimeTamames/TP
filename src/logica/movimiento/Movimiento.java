package logica.movimiento;

import exceptions.MovimientoInvalido;
import logica.Ficha;
import logica.tablero.Tablero;

public abstract class Movimiento {

    protected int fila;
    protected int columna;
    protected Ficha turno;

    public Movimiento(int columna, Ficha turno) {
        this.fila = 0;
        this.columna = columna;
        this.turno = turno;

    }

    //Costructor con fila != 0 para gravity y reversi

    public Movimiento(int fila, int columna, Ficha turno) {
        this.fila = fila;
        this.columna = columna;
        this.turno = turno;

    }

    public Ficha getJugador() {
        return this.turno;
    }

    public int getColumna() {
        return this.columna;
    }

    public int getFila() {
        return this.fila;
    }

    public abstract void ejecutaMovimiento(Tablero t) throws MovimientoInvalido;

    public abstract void undo(Tablero t);

}
