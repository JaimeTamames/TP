package logica.movimiento;

import exceptions.MovimientoInvalido;
import java.util.ArrayList;
import logica.Ficha;
import logica.tablero.Tablero;

public class MovimientoReversi extends Movimiento {

    private ArrayList<MovimientoReversi> undoStack;

    public MovimientoReversi(int fila, int columna, Ficha turno) {
        super(fila, columna, turno);

        this.undoStack = new ArrayList<>();
    }

    public boolean posicionValida(Tablero t) {

        boolean valido = false;

        if (validaPorDebajo(t)) {
            valido = true;
        }
        if (validaPorEncima(t)) {
            valido = true;
        }
        if (validaPorDerecha(t)) {
            valido = true;
        }
        if (validaPorIzquierda(t)) {
            valido = true;
        }
        if (validaDiagonalSupDer(t)) {
            valido = true;
        }
        if (validaDiagonalInfDer(t)) {
            valido = true;
        }
        if (validaDiagonalSupIzq(t)) {
            valido = true;
        }
        if (validaDiagonalInfIzq(t)) {
            valido = true;
        }
        if (valido) {
            t.ponerFicha(this.fila, this.columna, turno);
        }

        return valido;
    }

    private boolean validaPorEncima(Tablero t) {

        boolean valido = false;

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
                valido = true;

                for (int fil = f - 1; fil > this.fila; fil--) {

                    t.voltearFicha(fil, this.columna, turno);
                    MovimientoReversi m = new MovimientoReversi(fil, this.columna, turno);
                    undoStack.add(m);
                }
            }
        }
        return valido;
    }

    private boolean validaPorDebajo(Tablero t) {

        boolean valido = false;

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
                valido = true;

                for (int fil = f + 1; fil < this.fila; fil++) {

                    t.voltearFicha(fil, this.columna, turno);
                    MovimientoReversi m = new MovimientoReversi(fil, this.columna, turno);
                    undoStack.add(m);
                }
            }
        }
        return valido;
    }

    private boolean validaPorDerecha(Tablero t) {

        boolean valido = false;

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
                valido = true;

                for (int col = c - 1; col > this.columna; col--) {

                    t.voltearFicha(this.fila, col, turno);
                    MovimientoReversi m = new MovimientoReversi(this.fila, col, turno);
                    undoStack.add(m);
                }
            }
        }
        return valido;
    }

    private boolean validaPorIzquierda(Tablero t) {

        boolean valido = false;

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
                valido = true;

                for (int col = c + 1; col < this.columna; col++) {

                    t.voltearFicha(this.fila, col, turno);
                    MovimientoReversi m = new MovimientoReversi(this.fila, col, turno);
                    undoStack.add(m);
                }
            }
        }
        return valido;
    }

    private boolean validaDiagonalSupDer(Tablero t) {

        boolean valido = false;

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
                valido = true;

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
        return valido;
    }

    private boolean validaDiagonalSupIzq(Tablero t) {

        boolean valido = false;

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
                valido = true;

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
        return valido;
    }

    private boolean validaDiagonalInfDer(Tablero t) {

        boolean valido = false;

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
                valido = true;

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
        return valido;
    }

    private boolean validaDiagonalInfIzq(Tablero t) {

        boolean valido = false;

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
                valido = true;

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
        return valido;
    }

    public static int fichasVolteadas(int f, int c, Tablero t) {

        if (t.getFicha(f, c) != Ficha.VACIA) {
            
        } else {

            if (c >= t.getAncho() || c < 0
                    || f >= t.getAlto() || f < 0) {
                return 0;
            }

            if (t.getFicha(f, c) != Ficha.VACIA) {
                return 0;
            }
        }
        return 0;
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
        if (!posicionValida(t)) {
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
