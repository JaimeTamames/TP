package logica.movimiento;

import exceptions.MovimientoInvalido;
import java.util.ArrayList;
import logica.Ficha;
import logica.reglas.ReglasJuegoReversi;
import logica.tablero.Tablero;

public class MovimientoReversi extends Movimiento {

    private ArrayList<MovimientoReversi> undoStack;

    public MovimientoReversi(int fila, int columna, Ficha turno) {
        super(fila, columna, turno);

        this.undoStack = new ArrayList<>();
    }

    private void voltea(Tablero t) {
        if (ReglasJuegoReversi.validaPorDebajo(t, fila, columna, turno)) {
            this.volteaPorDebajo(t);
        }
        if (ReglasJuegoReversi.validaPorEncima(t, fila, columna, turno)) {
            this.volteaPorEncima(t);
        }
        if (ReglasJuegoReversi.validaPorDerecha(t, fila, columna, turno)) {
            this.volteaPorDerecha(t);
        }
        if (ReglasJuegoReversi.validaPorIzquierda(t, fila, columna, turno)) {
            this.volteaPorIzquierda(t);
        }
        if (ReglasJuegoReversi.validaDiagonalSupDer(t, fila, columna, turno)) {
            this.volteaDiagonalSupDer(t);
        }
        if (ReglasJuegoReversi.validaDiagonalInfDer(t, fila, columna, turno)) {
            this.volteaDiagonalInfDer(t);
        }
        if (ReglasJuegoReversi.validaDiagonalSupIzq(t, fila, columna, turno)) {
            this.volteaDiagonalSupIzq(t);
        }
        if (ReglasJuegoReversi.validaDiagonalInfIzq(t, fila, columna, turno)) {
            this.volteaDiagonalInfIzq(t);
        }
    }

    private void volteaPorEncima(Tablero t) {

        //Comprueba todas las posiciones por encima.
        if (this.fila <= t.getAlto() - 3) {

            int f = this.fila + 1;
            int numFichas = 0;

            while ((f < t.getAlto() - 1)
                    && (t.getFicha(f, this.columna) != Ficha.VACIA) && (t.getFicha(f, this.columna) != this.turno)) {
                numFichas++;
                f++;
            }
            if (numFichas != 0 && t.getFicha(f, this.columna) == this.turno) {

                for (int fil = f - 1; fil > this.fila; fil--) {

                    t.voltearFicha(fil, this.columna, turno);
                    MovimientoReversi m = new MovimientoReversi(fil, this.columna, turno);
                    undoStack.add(m);
                }

            }
        }
    }

    private void volteaPorDebajo(Tablero t) {

        //Comprueba todas las posiciones por debajo.
        if (this.fila >= 2) {

            int f = this.fila - 1;
            int numFichas = 0;

            while ((f > 0)
                    && (t.getFicha(f, this.columna) != Ficha.VACIA) && (t.getFicha(f, this.columna) != this.turno)) {
                numFichas++;
                f--;
            }
            if (numFichas != 0 && t.getFicha(f, this.columna) == this.turno) {

                for (int fil = f + 1; fil < this.fila; fil++) {

                    t.voltearFicha(fil, this.columna, turno);
                    MovimientoReversi m = new MovimientoReversi(fil, this.columna, turno);
                    undoStack.add(m);
                }

            }
        }

    }

    private void volteaPorDerecha(Tablero t) {

        //Comprueba todas las posiciones por la derecha.
        if (this.columna <= t.getAncho() - 3) {

            int c = this.columna + 1;
            int numFichas = 0;

            while ((c < t.getAncho() - 1)
                    && (t.getFicha(this.fila, c) != Ficha.VACIA) && (t.getFicha(this.fila, c) != this.turno)) {
                numFichas++;
                c++;
            }
            if (numFichas != 0 && t.getFicha(this.fila, c) == this.turno) {

                for (int col = c - 1; col > this.columna; col--) {

                    t.voltearFicha(this.fila, col, turno);
                    MovimientoReversi m = new MovimientoReversi(this.fila, col, turno);
                    undoStack.add(m);
                }

            }
        }
    }

    private void volteaPorIzquierda(Tablero t) {

        //Comprueba todas las posiciones por la izquierda.
        if (this.columna >= 2) {

            int c = this.columna - 1;
            int numFichas = 0;

            while ((c > 0)
                    && (t.getFicha(this.fila, c) != Ficha.VACIA) && (t.getFicha(this.fila, c) != this.turno)) {
                numFichas++;
                c--;
            }
            if (numFichas > 0 && t.getFicha(this.fila, c) == this.turno) {

                for (int col = c + 1; col < this.columna; col++) {

                    t.voltearFicha(this.fila, col, turno);
                    MovimientoReversi m = new MovimientoReversi(this.fila, col, turno);
                    undoStack.add(m);
                }

            }
        }

    }

    private void volteaDiagonalSupDer(Tablero t) {

        //Comprueba todas las posiciones en la diagonal superior derecha.
        if (this.fila <= t.getAlto() - 3 && this.columna <= t.getAncho() - 3) {

            int f = this.fila + 1;
            int c = this.columna + 1;
            int numFichas = 0;

            while ((f < t.getAlto() - 1) && (c < t.getAncho() - 1)
                    && (t.getFicha(f, c) != Ficha.VACIA) && (t.getFicha(f, c) != this.turno)) {
                numFichas++;
                f++;
                c++;
            }
            if (numFichas != 0 && t.getFicha(f, c) == this.turno) {

                while (numFichas > 0) {
                    numFichas--;
                    f--;
                    c--;
                    t.voltearFicha(f, c, turno);
                    MovimientoReversi m = new MovimientoReversi(f, c, turno);
                    undoStack.add(m);
                }

            }
        }
    }

    private void volteaDiagonalSupIzq(Tablero t) {

        //Comprueba todas las posiciones en la diagonal superior izquierda.
        if (this.fila <= t.getAlto() - 3 && this.columna >= 2) {

            int f = this.fila + 1;
            int c = this.columna - 1;
            int numFichas = 0;

            while ((f < t.getAlto() - 1) && (c > 0)
                    && (t.getFicha(f, c) != Ficha.VACIA) && (t.getFicha(f, c) != this.turno)) {
                numFichas++;
                f++;
                c--;
            }
            if (numFichas != 0 && t.getFicha(f, c) == this.turno) {

                while (numFichas > 0) {
                    numFichas--;
                    f--;
                    c++;
                    t.voltearFicha(f, c, turno);
                    MovimientoReversi m = new MovimientoReversi(f, c, turno);
                    undoStack.add(m);
                }

            }
        }

    }

    private void volteaDiagonalInfDer(Tablero t) {

        //Comprueba todas las posiciones en la diagonal inferior derecha.
        if (this.fila >= 2 && this.columna <= t.getAncho() - 3) {

            int f = this.fila - 1;
            int c = this.columna + 1;
            int numFichas = 0;

            while ((f > 0) && (c < t.getAncho() - 1)
                    && (t.getFicha(f, c) != Ficha.VACIA) && (t.getFicha(f, c) != this.turno)) {
                numFichas++;
                f--;
                c++;
            }
            if (numFichas != 0 && t.getFicha(f, c) == this.turno) {

                while (numFichas > 0) {
                    numFichas--;
                    f++;
                    c--;
                    t.voltearFicha(f, c, turno);
                    MovimientoReversi m = new MovimientoReversi(f, c, turno);
                    undoStack.add(m);
                }
            }
        }
    }

    private void volteaDiagonalInfIzq(Tablero t) {

        //Comprueba todas las posiciones en la diagonal inferior izquierda.
        if (this.fila >= 2 && this.columna >= 2) {

            int f = this.fila - 1;
            int c = this.columna - 1;
            int numFichas = 0;

            while ((f > 0) && (c > 0)
                    && (t.getFicha(f, c) != Ficha.VACIA) && (t.getFicha(f, c) != this.turno)) {
                numFichas++;
                f--;
                c--;
            }
            if (numFichas != 0 && t.getFicha(f, c) == this.turno) {

                while (numFichas > 0) {
                    numFichas--;
                    f++;
                    c++;
                    t.voltearFicha(f, c, turno);
                    MovimientoReversi m = new MovimientoReversi(f, c, turno);
                    undoStack.add(m);
                }

            }
        }

    }

    @Override
    public void ejecutaMovimiento(Tablero t) throws MovimientoInvalido {

        if (this.columna >= t.getAncho() || this.columna < 0
                || this.fila >= t.getAlto() || this.fila < 0) {
            throw new MovimientoInvalido("Posicion incorrecta");
        }

        if (t.getFicha(this.fila, this.columna) != Ficha.VACIA) {
            throw new MovimientoInvalido("Casilla ocupada");
        }

        //Comprueba si la posicion es valida y ejecuta el movimiento
        if (ReglasJuegoReversi.posicionValida(t, this.fila, this.columna, this.turno)) {
            this.voltea(t);
            t.ponerFicha(this.fila, this.columna, this.turno);

        } else {
            throw new MovimientoInvalido("Posicion no valida");
        }
    }

    @Override
    public void undo(Tablero t) {

        for (MovimientoReversi m : undoStack) {

            if (m.getJugador() == Ficha.BLANCAS) {
                t.voltearFicha(m.getFila(), m.getColumna(), Ficha.NEGRAS);
            } else {
                t.voltearFicha(m.getFila(), m.getColumna(), Ficha.BLANCAS);
            }

        }
        t.ponerFicha(this.fila, this.columna, Ficha.VACIA);
    }
}
